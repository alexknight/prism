package com.android.prism.threads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.MemUtils;

/**
 * Project: Prism
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
     * TODO: bugfix-只收到一次消息
     */
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        MonitorManager.getInstance().sMonitorHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MonitorType.MONITOR_MSG_TYPE_MEM:
                        Log.d(MonitorType.MONITOR_PERFORMANCE, "handleMessage: " + MemUtils.getUseSize());
                        break;
                }
            }
        };
    }
}