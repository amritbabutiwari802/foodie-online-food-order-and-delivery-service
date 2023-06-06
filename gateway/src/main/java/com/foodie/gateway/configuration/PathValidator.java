package com.foodie.gateway.configuration;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class PathValidator {

    public static final List<String> ignoredPaths = List.of(
            "/api/auth"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> ignoredPaths
            .stream()
            .noneMatch(url ->
                    serverHttpRequest.getURI().getPath().startsWith(url));
}
