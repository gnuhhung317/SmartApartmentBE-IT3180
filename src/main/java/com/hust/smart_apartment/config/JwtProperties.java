package com.hust.smart_apartment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.security.authentication.jwt")
public class JwtProperties {

    private String secret;
    private long tokenValidityInSeconds;
}
