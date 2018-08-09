package com.android.prism.observers;
import android.os.Message;

import com.android.prism.bean.CpuInfo;
import com.android.prism.constants.MonitorType;
import com.android.prism.subjects.MonitorManager;
import com.android.prism.utils.AppUtils;
import com.android.prism.utils.CpuUtils;

import java.util.ArrayList;


/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:51
 */


public class CpuObserver extends MonitorObserver {
    private ArrayList<CpuInfo> cpuInfos;

    public CpuObserver(MonitorManager monitorManager, ArrayList<CpuInfo> cpuInfos){
        this.monitorManager = monitorManager;
        this.monitorManager.register(this);
        this.cpuInfos = cpuInfos;
    }

    /**
     * 开始采集数据
     * TODO: 非Root手机采集不到CPU信息
     */
    @Override
    public void update() {
        CpuInfo cpuInfo = new CpuInfo();
        cpuInfo.setTimestamp(AppUtils.getCurrentTime());

        cpuInfo.setProcessRate(CpuUtils.getCpuRate(AppUtils.getInstance().getPid()));
        cpuInfos.add(cpuInfo);

        Message msg = Message.obtain();
        msg.obj = cpuInfo;
        msg.what = MonitorType.MONITOR_MSG_TYPE_CPU;
        monitorManager.sMonitorHandler.sendMessage(msg);
    }

    /**
     * 上报结果数据
     */
    @Override
    public void report() {

    }

}
