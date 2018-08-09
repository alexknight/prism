package com.android.prism;

import android.app.Application;
import android.content.Context;

import com.android.prism.crash.Cockroach;
import com.android.prism.crash.CrashHandler;
import com.android.prism.utils.AppUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Project: PrismSDK
 * @date : 2018/8/4 下午11:42
 * @author : Alex(qingge)
 */

public class Prism {
    private static volatile Prism sPrism;
    private Context context;
    private Performance mPerformance;

    private Prism (Context context){
        this.context = context;
        mPerformance = new Performance(context);
        AppUtils.getInstance().setAppContext(context).setPid(android.os.Process.myPid());
    }

    public static Prism getInstance(Context context) {
        if (sPrism == null) {
            synchronized (Prism.class) {
                if (sPrism == null) {
                    sPrism = new Prism(context);
                }
            }
        }
        return sPrism;
    }


    /**
     * mPerformance：开启内存，CPU，帧率监控（在looper线程中干货）->只起了两个线程looper，一个定时任务到期，
     *              会通知各个observer更新，observer则sendMessage，等待MoitorThread的统一处理
     */
    public void start() {
        // crash处理<->ui线程looper中
        Cockroach.install(new CrashHandler(this.context));
        // 内存泄漏
        LeakCanary.install((Application) this.context);
        mPerformance.start();
    }

    public void stop(){
        Cockroach.uninstall();
        mPerformance.stop();
    }

}
