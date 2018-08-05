package com.android.prism;

import android.app.Application;
import android.content.Context;

import com.android.prism.crash.Cockroach;
import com.android.prism.crash.CrashHandler;
import com.android.prism.utils.AppUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by qingge on 2018/8/4.
 */

public class PrismSDK {
    private static volatile PrismSDK sPrismSDK;
    private Context context;
    private Performance mPerformance;

    private PrismSDK (Context context){
        this.context = context;
        mPerformance = new Performance(context);
        AppUtils.getInstance().setAppContext(context);
    }

    public static PrismSDK getInstance(Context context) {
        if (sPrismSDK == null) {
            synchronized (PrismSDK.class) {
                if (sPrismSDK == null) {
                    sPrismSDK = new PrismSDK(context);
                }
            }
        }
        return sPrismSDK;
    }


    public void start() {
        // crash处理<->ui线程looper中
        Cockroach.install(new CrashHandler(this.context));
        // 内存泄漏
        LeakCanary.install((Application) this.context);
        // 开启内存，CPU，帧率监控
        mPerformance.start();
    }

    public void stop(){
        Cockroach.uninstall();
        mPerformance.stop();
    }

}
