package com.ibutch.apigateway.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * Created by butchsantos on 9/5/23.
 */
@Slf4j
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping
    public Mono<ResponseEntity<ProblemDetail>> fallbackResponse(
            ServerHttpRequest request,
            ServerWebExchange exchange
    ) {
        // https://datatracker.ietf.org/doc/html/rfc7807
        ProblemDetail pb = ProblemDetail.forStatus(500);
        pb.setTitle("FALLBACK_RESPONSE");
        pb.setType(URI.create("http://localhost/error-page-detail"));
        pb.setDetail(String
                .format("The requested endpoint: %s is currently not available.",
                    request.getPath()));
        // CUSTOM PROPERTY
        pb.setProperty("timestamp", LocalDateTime.now());
        return Mono.just(
            ResponseEntity
                .internalServerError()
                .body(pb)
        );
    }
}