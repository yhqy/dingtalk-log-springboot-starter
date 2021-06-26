package com.github.yhqy.dingtalk.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.yhqy.dingtalk.DingTalkSender;
import com.github.yhqy.dingtalk.DingTalkSpringUtil;
import com.github.yhqy.dingtalk.HostInfo;
import com.github.yhqy.dingtalk.DingTalkProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.github.yhqy.dingtalk.DingTalkAutoConfig.config;

@Slf4j
public class DingTalkLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent eventObject) {
        DingTalkProperties config = config();
        if (!config.isEnable()) {
            return;
        }

        Level level = eventObject.getLevel();
        if (!config.contain(level)) {
            return;
        }

        String levelStr = level.levelStr;
        String message = eventObject.getMessage();
        Throwable throwable = null;
        IThrowableProxy ithrowableProxy = eventObject.getThrowableProxy();
        if (ithrowableProxy instanceof ThrowableProxy) {
            ThrowableProxy throwableProxy = (ThrowableProxy) ithrowableProxy;
            throwable = throwableProxy.getThrowable();
        }
        DingTalkSender.send(levelStr, message, throwable);
    }
}
