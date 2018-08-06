package com.android.prism.observers;
import android.os.Message;

import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;


/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:51
 */


public class CpuObserver extends MonitorObserver {

    public CpuObserver(MonitorManager monitorManager){
        this.monitorManager = monitorManager;
        this.monitorManager.attach(this);
    }

    /**
     * 开始采集数据
     */
    @Override
    public void update() {
        Message msg = Message.obtain();
        msg.what = MonitorType.MONITOR_MSG_TYPE_CPU;
        monitorManager.monitorHandler.sendMessage(msg);
    }

}
