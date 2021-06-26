package com.github.yhqy.dingtalk;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import static com.github.yhqy.dingtalk.DingTalkAutoConfig.config;

public class DingTalkSender {

    private static String requestUrl = null;

    public static void send(String level, String message, Throwable throwable) {
        HostInfo hostInfo = hostInfo();
        StringBuilder logItem = new StringBuilder();
        logItem.append("应用：").append(hostInfo.getAppName()).append("\n");
        logItem.append("IP：").append(hostInfo.getIp()).append("\n");
        logItem.append("日志级别：").append(level).append("\n");
        logItem.append("发生时间：").append(DateUtil.now()).append("\n");
        logItem.append("描述：").append(message).append("\n");
        if (null != throwable) {
            String log = ExceptionUtil.stacktraceToString(throwable, 2000);
            logItem.append("异常：").append(log);
        }
        sendToDingTalk(logItem.toString());
    }

    private static void sendToDingTalk(String content) {
        if (StrUtil.isEmpty(content)) {
            return;
        }
        Message message = new Message(content);
        String json = JSONUtil.toJsonStr(message);
        HttpUtil.post(getRequestUrl(), json, 3000);
    }

    @Data
    private static class Message {
        private String msgtype = "text";
        private Content text;

        public Message() {
        }

        public Message(String content) {
            this.text = new Content(content);
        }
    }

    @Data
    private static class Content {
        private String content;

        public Content() {
        }

        public Content(String content) {
            this.content = content;
        }
    }

    private static String getRequestUrl() {
        if (null == requestUrl) {
            String token = config().getToken();
            requestUrl = String.format("https://oapi.dingtalk.com/robot/send?access_token=%s", token);
        }
        return requestUrl;
    }

    private static HostInfo hostInfo() {
        return DingTalkSpringUtil.getBean(HostInfo.class);
    }
}
