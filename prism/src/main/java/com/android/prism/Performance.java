package com.android.prism;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.prism.constants.MonitorType;
import com.android.prism.observers.CpuObserver;
import com.android.prism.observers.MemObserver;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.tasks.MonitorThread;
import com.android.prism.tasks.TimerThread;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午2:02
 */


class Performance {
    private Context mContext;
    private Handler monitorHandler;
    private MonitorManager monitorManager = MonitorManager.getInstance().setMonitorHandler(monitorHandler);

    Performance(Context context) {
        this.mContext = context;
    }

    void start() {
        new MemObserver(monitorManager);
        new CpuObserver(monitorManager);
        // 性能handleMessage线程
        new MonitorThread("MonitorThread",0).start();
        // 性能sendMessage线程
        new TimerThread("TimerThread", 0, MonitorType.MONITOR_HIGH_RATE).start();
    }

    void stop() {
        monitorManager.setState(0);
    }
}
