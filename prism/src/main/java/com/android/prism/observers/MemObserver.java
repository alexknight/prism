package com.android.prism.observers;
import android.os.Message;

import com.android.prism.bean.MemInfo;
import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.AppUtils;
import com.android.prism.utils.MemUtils;

import java.util.ArrayList;


/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:51
 */


public class MemObserver extends MonitorObserver {

    private ArrayList<MemInfo> memInfos;

    public MemObserver(MonitorManager monitorManager, ArrayList<MemInfo> memInfos){
        this.monitorManager = monitorManager;
        this.monitorManager.register(this);
        this.memInfos = memInfos;
    }

    /**
     * 开始采集内存数据
     */
    @Override
    public void update() {
        MemInfo memInfo = new MemInfo();
        memInfo.setTimestamp(AppUtils.getCurrentTime());
        memInfo.setTotalMemory(MemUtils.getTotalMemory());
        memInfo.setProcessUsedMemory(MemUtils.getUseSize());
        memInfos.add(memInfo);

        Message msg = Message.obtain();
        msg.what = MonitorType.MONITOR_MSG_TYPE_MEM;
        msg.obj = memInfo;
        monitorManager.sMonitorHandler.sendMessage(msg);
    }

    /**
     * 单个测试项上报结果数据，可以不实现
     */
    @Override
    public void report() {
    }

}
