package com.android.prism.constants;

/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午9:50
 */


public class MonitorType {
    public static final String MONITOR_PERFORMANCE = "MONITOR_PERFORMANCE";
    public static final String MONITOR_TYPE_APP_CRASH = "CRASH";//APP崩溃
    public static final String MONITOR_TYPE_APP_ANR = "ANR";//APP ANR
    public static final String MONITOR_TYPE_APP_BOOT = "BOOT";
    public static final String MONITOR_TYPE_FPS = "FPS";
    public static final String MONITOR_TYPE_MEM = "MEM";
    public static final String MONITOR_TYPE_ACTIVITY_LOAD = "PAGE_LOAD";

    public static final int MONITOR_MSG_TYPE_MEM = 1;
    public static final int MONITOR_MSG_TYPE_CPU = 2;

    public static final int MONITOR_HIGH_RATE = 500;
    public static final int MONITOR_MIDDLE_RATE = 1000;
    public static final int MONITOR_LOW_RATE = 1500;
}
