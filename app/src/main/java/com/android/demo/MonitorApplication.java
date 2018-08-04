package com.android.demo;

import android.app.Application;

import com.android.prism.PrismSDK;

/**
 * Created by qingge on 2018/8/4.
 */

public class MonitorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrismSDK.getInstance(this).start();
    }

}
