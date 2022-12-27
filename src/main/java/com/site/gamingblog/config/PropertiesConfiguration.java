package com.site.gamingblog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kkgamingblog")
@Getter
@Setter
public class PropertiesConfiguration {

    private String host;
}
