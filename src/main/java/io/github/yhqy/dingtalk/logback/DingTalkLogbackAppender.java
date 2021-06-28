package io.github.yhqy.dingtalk.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import io.github.yhqy.dingtalk.DingTalkSender;
import io.github.yhqy.dingtalk.DingTalkProperties;
import io.github.yhqy.dingtalk.DingTalkAutoConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingTalkLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent eventObject) {
        DingTalkProperties config = DingTalkAutoConfig.config();
        if (!config.isEnable()) {
            return;
        }

        Level level = eventObject.getLevel();
        if (!config.contain(level)) {
            return;
        }

        String levelStr = level.levelStr;
        String message = eventObject.getFormattedMessage();
        Throwable throwable = null;
        IThrowableProxy ithrowableProxy = eventObject.getThrowableProxy();
        if (ithrowableProxy instanceof ThrowableProxy) {
            ThrowableProxy throwableProxy = (ThrowableProxy) ithrowableProxy;
            throwable = throwableProxy.getThrowable();
        }
        DingTalkSender.send(levelStr, message, throwable);
    }
}
