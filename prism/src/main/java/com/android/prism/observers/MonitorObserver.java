package com.android.prism.observers;

import com.android.prism.subjects.MonitorManager;

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
