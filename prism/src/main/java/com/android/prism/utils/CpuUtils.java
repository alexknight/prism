package com.android.prism.utils;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午6:43
 */


public class CpuUtils {

    public static int getCpuRate(int pid){
        long mLastCpuTime = 0;//当前手机的CPU总时间
        long mLastAppTime = 0;//当前app的CPU耗时
        RandomAccessFile procStatFile = null;
        RandomAccessFile appStatFile = null;
        int cpuRateInt = -1;

        try {
            procStatFile = new RandomAccessFile("/proc/stat", "r");
            appStatFile = new RandomAccessFile("/proc/" + pid + "/stat", "r");

            //文件开头
            procStatFile.seek(0);
            appStatFile.seek(0);

            String proStatSrc = procStatFile.readLine();
            String appStatSrc = appStatFile.readLine();

            String[] proStats = null;
            String[] appStats = null;
            if (proStatSrc != null || appStatSrc != null) {
                proStats = proStatSrc.trim().split(" ");
                appStats = appStatSrc.trim().split(" ");
            }

            long cpuTime = 0L;
            long appTime = 0L;
            if (proStats != null && proStats.length >= 9) {
                for (int i = 2; i <= 8; i++) {
                    cpuTime += Long.valueOf(proStats[i]);
                }
            }

            if (appStats != null && appStats.length >= 15) {
                appTime = Long.valueOf(appStats[13]) + Long.valueOf(appStats[14]);
            }

            mLastCpuTime = cpuTime;
            mLastAppTime = appTime;

            double cpuRate = (double) (appTime - mLastAppTime) / (double) (cpuTime - mLastCpuTime);
            mLastCpuTime = cpuTime;
            mLastAppTime = appTime;

            cpuRateInt = (int) (cpuRate * 100);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpuRateInt;
    }
}
