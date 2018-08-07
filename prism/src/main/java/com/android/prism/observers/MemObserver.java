package com.android.prism.observers;
import android.os.Message;

import com.android.prism.bean.MemInfo;
import com.android.prism.bean.MonitorResult;
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
     * TODO:新增一个条件判断，当在Activity对应生命周期下，数据采集才开启
     */
    @Override
    public void update() {
        MemInfo memInfo = new MemInfo();
        memInfo.setTimestamp(AppUtils.getCurrentTime());
        memInfo.setTotalMemory(MemUtils.getTotalMemory());
        memInfo.setProcessUsedMemory(MemUtils.getUseSize());
        MonitorResult.memInfos.add(memInfo);

        Message msg = Message.obtain();
        msg.what = MonitorType.MONITOR_MSG_TYPE_MEM;
        msg.obj = memInfo;
        monitorManager.sMonitorHandler.sendMessage(msg);
    }

}
