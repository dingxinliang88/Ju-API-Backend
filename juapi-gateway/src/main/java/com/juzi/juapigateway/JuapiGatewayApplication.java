package com.juzi.juapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author codejuzi
 */
@SpringBootApplication
@EnableDubbo
public class JuapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuapiGatewayApplication.class, args);
    }

}
