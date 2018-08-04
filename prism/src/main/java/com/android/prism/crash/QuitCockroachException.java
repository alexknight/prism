package com.android.prism.crash;

/**
 * Created by qingge on 2018/8/4.
 */

public class QuitCockroachException extends RuntimeException {
    public QuitCockroachException(String message) {
        super(message);
    }
}
