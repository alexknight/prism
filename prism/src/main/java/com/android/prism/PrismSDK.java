package com.android.prism;

import android.content.Context;

import com.android.prism.crash.Cockroach;
import com.android.prism.crash.CrashHandler;

/**
 * Created by qingge on 2018/8/4.
 */

public class PrismSDK {
    private static volatile PrismSDK sPrismSDK;
    private Context context;

    private PrismSDK (Context context){
        this.context = context;
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
    }

}
