package com.github.yhqy.dingtalk;

import ch.qos.logback.classic.Level;
import lombok.Getter;

public enum LogLevel {
    TRACE(Level.TRACE),
    DEBUG(Level.DEBUG),
    INFO(Level.INFO),
    WARN(Level.WARN),
    ERROR(Level.ERROR),
    OFF(Level.OFF);

    @Getter
    private final Level level;

    LogLevel(Level level) {
        this.level = level;
    }
}
