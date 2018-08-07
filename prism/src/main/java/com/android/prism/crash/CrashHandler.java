package com.android.prism.crash;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by qingge on 2018/8/4.
 */

public class CrashHandler implements Cockroach.ExceptionHandler {

    private final Context context;

    public CrashHandler(Context context){
        this.context = context;
    }
    @Override
    public void handlerException(final Thread thread, final Throwable throwable) {
        //开发时使用Cockroach可能不容易发现bug，所以建议开发阶段在handlerException中用Toast谈个提示框，
        //由于handlerException可能运行在非ui线程中，Toast又需要在主线程，所以new了一个new Handler(Looper.getMainLooper())，
        //所以千万不要在下面的run方法中执行耗时操作，因为run已经运行在了ui线程中。
        //new Handler(Looper.getMainLooper())只是为了能弹出个toast，并无其他用途
        Toast.makeText(context, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                    Log.e("AndroidRuntime","--->CockroachException:"+thread+"<---",throwable);
                    Toast.makeText(context, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
                } catch (Throwable e) {

                }
            }
        });
    }
}
