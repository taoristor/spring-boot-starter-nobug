package com.github.taoristor.nobug.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.nobug")
@Component
public class NoBugConfig {
    private Boolean forever = false;

    public Boolean getForever() {
        return forever;
    }

    public void setForever(Boolean forever) {
        this.forever = forever;
    }
}
