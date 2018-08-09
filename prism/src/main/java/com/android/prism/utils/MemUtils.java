package com.android.prism.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/5 下午8:54
 */


public class MemUtils {
    /**
     * 获取当前应用可用总内存 单位M
     *
     * @return
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().maxMemory() >> 20;
    }

    /**
     * 获取当前应用所占内存 单位M
     *
     * @return
     */
    public static long getUseSize() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) >> 10;
    }

    /**
     * 获取当前手机总内存大小
     *
     * @return
     */
    public static String getPhoneTotalRAM() {

        RandomAccessFile reader = null;
        String load;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam;
        String lastValue = "";
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
            }
            reader.close();
            totRam = Double.parseDouble(value);
            lastValue = twoDecimalForm.format(totRam).concat(" KB");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lastValue;
    }
}
