# dingtalk-log-springboot-starter

### 介绍
将应用错误日志发送到钉钉

### 使用

#### 安装
首先deploy到私仓（最近比较忙，还未上传到中央仓库）
然后引用
``` 
<dependency>
    <groupId>io.github.yhqy</groupId>
    <artifactId>dingtalk-log-springboot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#### 配置

在logback.xml中引入
```
<include resource="dingtalk-logback-appender.xml"/>
并在root中添加配置appender
<root level="INFO">
    ...其他appender
    <appender-ref ref="DingTalkLoggerAppender"/>
</root>
```

在application.yml或者properties中配置
```
logger:
  dingtalk:
    enable: true
    token: 换成自己的token
    include-levels: [ERROR,WARN] //哪些级别的日志会发到钉钉群
```

#### 钉钉配置
1. 新建钉钉群
2. 新建机器人，选择webhook机器人
3. 填写相关信息，在最后会有一个webhook地址，后面有个token复制配到上面
