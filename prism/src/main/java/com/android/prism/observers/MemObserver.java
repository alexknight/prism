package com.android.prism.observers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MemObserver(MonitorManager monitorManager){
        this.monitorManager = monitorManager;
        this.monitorManager.attach(this);
    }

    /**
     * 开始采集数据
     */
    @Override
    public void update() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                MemUtils.getUseSize();
                Log.d(MonitorType.MONITOR_PERFORMANCE, MonitorType.MONITOR_TYPE_MEM+": "+MemUtils.getUseSize());
                Toast.makeText(AppUtils.getInstance().getAppContext(),
                        MonitorType.MONITOR_TYPE_MEM +": "+ MemUtils.getUseSize(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
