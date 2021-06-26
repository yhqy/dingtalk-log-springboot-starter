package com.github.yhqy.dingtalk;

public class DingTalkAppenderConfigException extends RuntimeException {
    public DingTalkAppenderConfigException() {
    }

    public DingTalkAppenderConfigException(String message) {
        super(message);
    }

    public DingTalkAppenderConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DingTalkAppenderConfigException(Throwable cause) {
        super(cause);
    }

    public DingTalkAppenderConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
