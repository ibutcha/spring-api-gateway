package com.ibutch.apigateway.configs.observability;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

/**
 * Created by butchsantos on 9/6/23.
 */
@Configuration(proxyBeanMethods = false)
public class TraceIdWebFilter {
    @Bean
    WebFilter traceIdInResponseFilter(Tracer tracer) {
        return (exchange, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                exchange
                    .getResponse()
                    .getHeaders()
                    .set("traceId", currentSpan.context().traceId());
            }
            return chain.filter(exchange);
        };
    }
}