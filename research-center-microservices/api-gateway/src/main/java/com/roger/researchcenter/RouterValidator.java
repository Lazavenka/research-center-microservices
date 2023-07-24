package com.roger.researchcenter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints= List.of(
            "/auth/login",
            "/auth/register",
            "/auth/confirm",
            "/test/get_users",
            "/departments/public",
            "/laboratories/public",
            "/equipment/public",
            "/equipment_types/public"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
