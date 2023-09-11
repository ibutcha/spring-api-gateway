package com.ibutch.apigateway.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * Created by butchsantos on 9/11/23.
 */
@Configuration
@EnableRedisWebSession
public class Redis {

    /**
     * Auth web session.
     * */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
