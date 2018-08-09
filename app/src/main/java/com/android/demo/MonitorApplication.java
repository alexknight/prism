package com.android.demo;

import android.app.Application;

import com.android.prism.Prism;

/**
 * Created by qingge on 2018/8/4.
 */

public class MonitorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Prism.getInstance(this).start();
    }

}
