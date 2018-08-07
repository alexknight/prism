package com.android.prism.tasks;

import android.os.HandlerThread;
import com.android.prism.subjects.MonitorManager;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午2:00
 */

public class TimerThread extends HandlerThread {

    private float mInterval;

    public TimerThread(String name,  float interval) {
        super(name);
        this.mInterval = interval;
    }

    public TimerThread(String name, int priority, float interval) {
        super(name, priority);
        this.mInterval = interval;
    }

    /**
     * TODO: bugfix-只收到一次消息
     */
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        try {
            Thread.sleep((long) this.mInterval);
            // 开始模块驱动性能消息更新
            MonitorManager.getInstance().setState(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}