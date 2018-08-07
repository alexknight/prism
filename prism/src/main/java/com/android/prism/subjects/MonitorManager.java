package com.android.prism.subjects;

import android.os.Handler;

import com.android.prism.constants.MonitorType;
import com.android.prism.constants.Stats;
import com.android.prism.observers.MonitorObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: PrismSDK
 * Function: 性能的Subject类
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:42
 */


public class MonitorManager {
    public Handler sMonitorHandler;
    private List<MonitorObserver> monitorObservers = new ArrayList<MonitorObserver>();
    private int state = 0;

    private volatile static MonitorManager monitorManager;
    private MonitorManager (){}
    public static MonitorManager getInstance() {
        if (monitorManager == null) {
            synchronized (MonitorManager.class) {
                if (monitorManager == null) {
                    monitorManager = new MonitorManager();
                }
            }
        }
        return monitorManager;
    }

    public MonitorManager setMonitorHandler(Handler monitorHandler) {
        this.sMonitorHandler = monitorHandler;
        return this;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        if (this.state == 1 && Stats.MONITOR_START){
            notifyAllObservers();
        }

    }

    public void attach(MonitorObserver observer){
        monitorObservers.add(observer);
    }

    public void notifyAllObservers(){
        for (MonitorObserver observer : monitorObservers) {
            observer.update();
        }
    }
}
