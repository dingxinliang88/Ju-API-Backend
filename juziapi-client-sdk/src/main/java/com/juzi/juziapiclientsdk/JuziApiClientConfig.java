package com.juzi.juziapiclientsdk;

import com.juzi.juziapiclientsdk.client.JuziApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * SDK配置类
 *
 * @author codejuzi
 */
@Data
@ComponentScan
@Configuration
@ConfigurationProperties(prefix = "juzi.api.client")
public class JuziApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public JuziApiClient juziApiClient() {
        return new JuziApiClient(accessKey, secretKey);
    }
}
