package com.android.prism.bean;

/**
 * Project: PrismSDK
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午10:31
 */


public class CpuInfo {
    private int processRate;
    private int totoalRate;
    private String timestamp;
    private StackInfo stackInfo;

    public int getProcessRate() {
        return processRate;
    }

    public void setProcessRate(int processRate) {
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
