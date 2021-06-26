package com.github.yhqy.dingtalk;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.stereotype.Component;

@Component
@Data
public class HostInfo implements InitializingBean {

    @Value("${spring.application.name}")
    private String appName;

    private String ip;

    @Autowired
    private InetUtils inetUtils;


    @Override
    public void afterPropertiesSet() throws Exception {
        this.ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
    }
}
