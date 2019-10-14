package com.bn.admin.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @Author:  jaco
* @Date:  2019/2/12
*/
@Component
@ConfigurationProperties(prefix="xxl.job.executor")
@Data
public class XxlJobDataConfig {

    private String addresses;

    private String appName;

    private String ip;

    private int port;

    private String accessToken;

    private String logPath;

    private int logRetentionDays;
}
