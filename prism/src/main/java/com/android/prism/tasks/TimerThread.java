package com.android.prism.tasks;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.MemUtils;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午2:00
 */

public class TimerThread extends HandlerThread {

    private Handler mHandler;
    private float mInterval;

    public TimerThread(String name, Handler handler, float interval) {
        super(name);
        this.mHandler = handler;
        this.mInterval = interval;
    }

    public TimerThread(String name, Handler handler, int priority, float interval) {
        super(name, priority);
        this.mHandler = handler;
        this.mInterval = interval;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Looper looper = this.getLooper();
        try {
            Thread.sleep((long) this.mInterval);
            // 开始模块驱动性能消息更新
            MonitorManager.getInstance().setState(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}