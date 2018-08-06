package com.android.prism.observers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.MemUtils;

import java.security.PublicKey;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:41
 */


public abstract class MonitorObserver {
    protected MonitorManager monitorManager;
    public abstract void update();
}
