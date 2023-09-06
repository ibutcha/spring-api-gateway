package com.ibutch.apigateway.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * Created by butchsantos on 9/5/23.
 */
@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    //   https://boottechnologies-ci.medium.com/spring-webflux-rest-api-global-exception-handling-278f4095e45b
    @Override
    public Map<String, Object> getErrorAttributes(
            ServerRequest request,
            ErrorAttributeOptions options) {

        Throwable error = super.getError(request);
        Map<String, Object> map = super.getErrorAttributes(
                request, options);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", error.getMessage());
        return map;
    }
}
