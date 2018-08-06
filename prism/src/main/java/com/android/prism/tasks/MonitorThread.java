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


    public MonitorThread(String name) {
        super(name);
    }

    public MonitorThread(String name, int priority) {
        super(name, priority);
    }

    /**
     * TODO: 需要在这里做跟TimerThread的消息同步，不然handleMessage无效
     */
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Looper looper = this.getLooper();
        new Handler(looper){
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