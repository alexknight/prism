package com.android.prism.observers;
import android.os.Message;

import com.android.prism.bean.MemInfo;
import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.AppUtils;
import com.android.prism.utils.MemUtils;


/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:51
 */


public class MemObserver extends MonitorObserver {


    public MemObserver(MonitorManager monitorManager){
        this.monitorManager = monitorManager;
        this.monitorManager.attach(this);
    }

    /**
     * 开始采集数据
     */
    @Override
    public void update() {
        Message msg = Message.obtain();
        msg.what = MonitorType.MONITOR_MSG_TYPE_MEM;
        MemInfo memInfo = new MemInfo();
        memInfo.setTimestamp(AppUtils.getCurrentTime());
        memInfo.setTotalMemory(MemUtils.getTotalMemory());
        memInfo.setProcessUsedMemory(MemUtils.getUseSize());
        msg.obj = memInfo;
        monitorManager.monitorHandler.sendMessage(msg);
    }

}
