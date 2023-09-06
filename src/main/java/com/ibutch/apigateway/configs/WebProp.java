package com.ibutch.apigateway.configs;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by butchsantos on 9/6/23.
 */
@Configuration
public class WebProp {
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
