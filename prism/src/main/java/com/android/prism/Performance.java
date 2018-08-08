package com.android.prism;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.android.prism.constants.MonitorType;
import com.android.prism.constants.Stats;
import com.android.prism.observers.CpuObserver;
import com.android.prism.observers.MemObserver;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.tasks.MonitorThread;
import com.android.prism.tasks.TimerThread;
import com.android.prism.utils.AppUtils;



/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午2:02
 */


public class Performance implements Application.ActivityLifecycleCallbacks{
    private Context mContext;
    private Handler monitorHandler;
    private MonitorManager monitorManager;
    private String TAG = "Performance";
    public Env env;

    Performance(Context context) {
        this.mContext = context;
        this.monitorManager = MonitorManager.getInstance().setMonitorHandler(monitorHandler);
    }

    void init(){
        long time = System.currentTimeMillis();
        env = new Env.Builder().setAppStartTime(time).setBootActivity(AppUtils.getLauncherActivity(this.mContext)).build();
    }

    void start() {

        init();
        new MemObserver(monitorManager);
        new CpuObserver(monitorManager);
        // 性能handleMessage线程
        new MonitorThread("MonitorThread",0).start();
        // 性能sendMessage线程
        new TimerThread("TimerThread",MonitorType.MONITOR_HIGH_RATE).start();
        if (mContext instanceof Application) {
            ((Application) mContext).registerActivityLifecycleCallbacks(this);
        }
    }

    void stop() {
        Stats.PERFORMACE_START = false;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Stats.PERFORMACE_START = true;
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
