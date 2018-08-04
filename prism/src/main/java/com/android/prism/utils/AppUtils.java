package com.android.prism.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by qingge on 2018/8/4.
 */

public class AppUtils {

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
}
