package com.android.prism.subjects;

import android.util.Log;
import android.widget.Toast;

import com.android.prism.constants.MonitorType;
import com.android.prism.observers.MonitorObserver;
import com.android.prism.utils.AppUtils;
import com.android.prism.utils.MemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: PrismSDK
 * Function: 性能的Subject类
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:42
 */


public class MonitorManager {
    private List<MonitorObserver> monitorObservers = new ArrayList<MonitorObserver>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(MonitorObserver observer){
        monitorObservers.add(observer);
    }


    /**
     * TODO: 后续改成消息模式，通过往looper中塞消息，来执行采集（按线程）
     */
    public void notifyAllObservers(){
        for (MonitorObserver observer : monitorObservers) {
            observer.update();
        }
    }
}
