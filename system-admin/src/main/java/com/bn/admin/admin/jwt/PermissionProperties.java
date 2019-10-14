package com.bn.admin.admin.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "my.shiro")
public class PermissionProperties {
    private String anonUrl;

    private long jwtTimeOut;
}
