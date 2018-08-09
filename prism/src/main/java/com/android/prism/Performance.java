package com.android.prism;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.android.prism.bean.CpuInfo;
import com.android.prism.bean.MemInfo;
import com.android.prism.constants.MonitorType;
import com.android.prism.constants.Stats;
import com.android.prism.observers.CpuObserver;
import com.android.prism.observers.MemObserver;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.tasks.MonitorThread;
import com.android.prism.tasks.TimerThread;
import com.android.prism.utils.AppUtils;
import com.android.prism.utils.RootUtil;

import java.util.ArrayList;
import java.util.Map;


/**
 * Project: PrismSDK
 * TODO: 目前Fragment的测试无法正常统计页面加载时间
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午2:02
 */


public class Performance implements Application.ActivityLifecycleCallbacks{
    private Context mContext;
    private Handler monitorHandler;
    private MonitorManager monitorManager;
    private String TAG = "Performance";
    public static Map<String,String> pageMap;
    public Env env;

    // cpu测试数据
    private ArrayList<CpuInfo> cpuInfos = new ArrayList<>();
    // mem测试数据
    private ArrayList<MemInfo> memInfos = new ArrayList<>();
    // 启动时间
    private long bootCost;



    Performance(Context context) {
        this.mContext = context;
        this.monitorManager = MonitorManager.getInstance().setMonitorHandler(monitorHandler);
    }

    void init(){
        long time = System.currentTimeMillis();
        env = new Env.Builder().setAppStartTime(time)
                .setBootActivity(AppUtils.getLauncherActivity(this.mContext))
                .build();
        new MemObserver(monitorManager, memInfos);
        new CpuObserver(monitorManager, cpuInfos);
        if (mContext instanceof Application) {
            ((Application) mContext).registerActivityLifecycleCallbacks(this);
        }
        Stats.IS_ROOT = RootUtil.isRooted();
    }

    void start() {
        init();
        // 性能handleMessage线程
        new MonitorThread("MonitorThread",0).start();
        // 性能sendMessage线程
        new TimerThread("TimerThread",MonitorType.MONITOR_HIGH_RATE).start();

    }

    void stop() {
        Stats.PERFORMACE_START = false;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Stats.PERFORMACE_START = true;
        final String activityName = activity.getClass().getName();
        if (activityName.equals(env.getActivity())){
            final View view = activity.getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
                    @Override
                    public void onWindowFocusChanged(boolean hasFocus) {
                        if (hasFocus) {
                            bootCost = System.currentTimeMillis()-env.getAppStartTime();
                            Log.d(TAG, "启动时间: " + bootCost);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                view.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
                            }
                        }
                    }
                });
            }
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Stats.PERFORMACE_START = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (AppUtils.isForegroundApp(mContext)){
            Stats.PERFORMACE_START = true;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Stats.PERFORMACE_START = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Stats.PERFORMACE_START = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
