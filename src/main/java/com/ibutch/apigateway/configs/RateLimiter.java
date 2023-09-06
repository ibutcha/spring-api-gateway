package com.ibutch.apigateway.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Created by butchsantos on 9/5/23.
 */
@Slf4j
@Configuration
public class RateLimiter {
    @Bean("rateLimiterIdentifier")
    KeyResolver rateLimiterIdentifier() {
        return (exchange -> Mono.just(
            Objects.requireNonNull(exchange
                .getRequest()
                .getRemoteAddress())
                .getAddress()
                .getHostAddress()));
    }
}