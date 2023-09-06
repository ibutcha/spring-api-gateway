package com.ibutch.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    @RestartScope
    @ServiceConnection(value = "redis", name = "redis")
    GenericContainer<?> redisContainer() {
        return new GenericContainer<>(DockerImageName
            .parse("redis:alpine3.18"))
            .withExposedPorts(6379);
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(TestApplication.class)
                .run(args);
    }
}

