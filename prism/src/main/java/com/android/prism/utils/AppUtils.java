package com.android.prism.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.android.prism.bean.StackInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Project: PrismSDK
 * @date : 2018/8/4 下午11:42
 * @author : Alex(qingge)
 */

public class AppUtils {

    private volatile static AppUtils appUtils;
    private Context appContext;
    private int pid;

    private AppUtils (){
    }

    public static AppUtils getInstance() {
        if (appUtils == null) {
            synchronized (AppUtils.class) {
                if (appUtils == null) {
                    appUtils = new AppUtils();
                }
            }
        }
        return appUtils;
    }

    public AppUtils setAppContext(Context appContext) {
        this.appContext = appContext;
        return this;
    }

    public Context getAppContext(){
        return appContext;
    }

    public int getPid() {
        return pid;
    }

    public AppUtils setPid(int pid) {
        this.pid = pid;
        return this;
    }



    /**
     * 判断APP是否为前台应用
     * @return
     */
    public static boolean isForegroundApp(Context mContext) {
        ActivityManager ac = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = mContext.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningApps = ac.getRunningAppProcesses();

        if (packageName == null || runningApps == null || runningApps.isEmpty()) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo app : runningApps) {
            if (app.processName.equals(packageName) && app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public static StackInfo getRuntimeStack() {
        StackInfo stackInfo = new StackInfo();
        for (StackTraceElement i : Thread.currentThread().getStackTrace()) {
            // TODO: 新增StackInfo判断条件
            if (i != null){
                stackInfo.setFileName(i.getFileName());
                stackInfo.setMethodName(i.getMethodName());
                stackInfo.setStackMsg(i.toString());
                break;
            }
        }
        return stackInfo;
    }
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getLauncherActivity(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> packageInfos = context.getPackageManager().queryIntentActivities(intent, 0);
        if (packageInfos == null || packageInfos.isEmpty()) {
            return null;
        }

        for (int i = 0; i < packageInfos.size(); i++) {
            String launcherActivityName = packageInfos.get(i).activityInfo.name;
            String launcherPackageName = packageInfos.get(i).activityInfo.packageName;
            if (!TextUtils.isEmpty(launcherPackageName) && launcherPackageName.equals(context.getPackageName())) {
                return launcherActivityName;
            }
        }

        return null;
    }
}
