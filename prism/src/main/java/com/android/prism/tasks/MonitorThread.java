package com.android.prism.tasks;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.android.prism.constants.MonitorType;
import com.android.prism.utils.MemUtils;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午2:00
 */

public class MonitorThread extends HandlerThread {

    private Handler handler;

    public MonitorThread(String name, Handler handler) {
        super(name);
        this.handler = handler;
    }

    public MonitorThread(String name, Handler handler, int priority) {
        super(name, priority);
        this.handler = handler;
    }

    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Looper looper = this.getLooper();
        handler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MonitorType.MONITOR_MSG_TYPE_MEM:
                        Log.d(MonitorType.MONITOR_PERFORMANCE, "handleMessage: " + MemUtils.getUseSize());
                }
            }
        };
    }
}