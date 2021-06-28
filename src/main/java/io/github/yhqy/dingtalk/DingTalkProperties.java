package io.github.yhqy.dingtalk;

import ch.qos.logback.classic.Level;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties(prefix = "logger.dingtalk")
public class DingTalkProperties {
    private boolean enable;
    private String token;
    private Set<LogLevel> includeLevels;

    public boolean contain(Level level) {
        for (LogLevel includeLevel : includeLevels) {
            if(includeLevel.getLevel().equals(level)) {
                return true;
            }
        }
        return false;
    }


}
