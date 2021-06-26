package com.github.yhqy.dingtalk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ComponentScan("com.github.yhqy.dingtalk")
@EnableConfigurationProperties(DingTalkProperties.class)
public class DingTalkAutoConfig implements InitializingBean {

    private DingTalkProperties properties;

    public DingTalkAutoConfig(DingTalkProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!properties.isEnable()) {
            return;
        }
        if (StrUtil.isEmpty(properties.getToken())) {
            throw new DingTalkAppenderConfigException("logger.dingtalk.token can't be empty, please check your config");
        }
        Set<LogLevel> includeLevels = properties.getIncludeLevels();
        if (CollUtil.isEmpty(includeLevels)) {
            throw new DingTalkAppenderConfigException("logger.dingtalk.include-levels can't be empty, please check your config");
        }
    }

    public static DingTalkProperties config() {
        return DingTalkSpringUtil.getBean(DingTalkProperties.class);
    }
}
