package com.android.prism.bean;

/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午10:31
 */


public class CpuInfo {
    private double processRate;
    private int totoalRate;
    private String timestamp;
    private StackInfo stackInfo;

    public double getProcessRate() {
        return processRate;
    }

    public void setProcessRate(double processRate) {
        this.processRate = processRate;
    }

    public int getTotoalRate() {
        return totoalRate;
    }

    public void setTotoalRate(int totoalRate) {
        this.totoalRate = totoalRate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public StackInfo getStackInfo() {
        return stackInfo;
    }

    public void setStackInfo(StackInfo stackInfo) {
        this.stackInfo = stackInfo;
    }
}
