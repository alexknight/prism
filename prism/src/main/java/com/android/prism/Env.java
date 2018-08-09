package com.android.prism;

/**
 * Project: Prism
 *
 * @author : Alex(qingge)
 * @date : 2018/8/8 下午6:41
 */


public class Env {

    private long mAppStartTime;
    private String mActivity;

    private Env(Builder builder){
        mAppStartTime = builder.mAppStartTime;
        mActivity = builder.mActivity;
    }

    public long getAppStartTime() {
        return mAppStartTime;
    }

    public String getActivity() {
        return mActivity;
    }

    public static class Builder {
        private long mAppStartTime;
        private String mActivity;

        public Builder setAppStartTime(long time) {
            mAppStartTime = time;
            return this;
        }

        public Builder setBootActivity(String activity) {
            mActivity = activity;
            return this;
        }

        public Env build() {
            return new Env(this);
        }
    }
}
