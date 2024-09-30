package com.plataformae.ws.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class SecurityProperties {

    private String prefixes;

    public String getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(String prefixes) {
        this.prefixes = prefixes;
    }

    public List<String> getPermitAllPaths() {
        return Arrays.stream(prefixes.split(","))
                .map(prefix -> prefix + "**")
                .toList();
    }
}
