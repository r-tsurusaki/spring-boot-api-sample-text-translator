package com.translator.gwa.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.api.translator-text-api")
@Data
public class TranslatorTextConfig {

    private String apiVersion;
    private String subscriptionKey;
    private String endpoint;
}
