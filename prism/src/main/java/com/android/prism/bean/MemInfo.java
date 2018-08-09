package com.android.prism.bean;

/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/6 下午4:56
 */


public class MemInfo {
    private String timestamp;

    private StackInfo stackInfo;

    private long processUsedMemory;

    private long totalMemory;

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

    public long getProcessUsedMemory() {
        return processUsedMemory;
    }

    public void setProcessUsedMemory(long processUsedMemory) {
        this.processUsedMemory = processUsedMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }
}
