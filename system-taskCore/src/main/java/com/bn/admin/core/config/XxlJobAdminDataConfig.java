package com.bn.admin.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @Author:  jaco
* @Date:  2019/2/13
*/
@Component
@ConfigurationProperties(prefix="xxl.job")
@Data
public class XxlJobAdminDataConfig {

    private String loginUsername = "admin";

    private String loginPassword = "123456";

    private String i18n;

    private String accessToken;
}
