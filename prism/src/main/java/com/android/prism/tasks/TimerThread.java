package com.android.prism.tasks;

import android.os.HandlerThread;

import com.android.prism.constants.Stats;
import com.android.prism.subjects.MonitorManager;

/**
 * Project: Prism
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


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
    }

    @Override
    public void run(){
        while (true){
            if (Stats.PERFORMACE_START){
                MonitorManager.getInstance().updateObservers();
            }
            try {
                Thread.sleep((long) this.mInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}