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
    private boolean mStart;
    private boolean mIsForeground;//APP是否位于前台

    Performance(Context context) {
        this.mContext = context;
        this.monitorManager = MonitorManager.getInstance().setMonitorHandler(monitorHandler);
    }

    void start() {
        mIsForeground = true;
        Stats.MONITOR_START = true;
        new MemObserver(monitorManager);
//        new CpuObserver(monitorManager);
        // 性能handleMessage线程
        new MonitorThread("MonitorThread",0).start();
        // 性能sendMessage线程
        new TimerThread("TimerThread", 0, MonitorType.MONITOR_HIGH_RATE).start();
    }

    void stop() {
        mStart = false;
        monitorManager.setState(0);
        Stats.MONITOR_START = false;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Stats.MONITOR_START = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (mStart) {
            return;
        }
        if (AppUtils.isForegroundApp(mContext)  && !mIsForeground){
            mIsForeground = true;
            Stats.MONITOR_START = true;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Stats.MONITOR_START = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Stats.MONITOR_START = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
