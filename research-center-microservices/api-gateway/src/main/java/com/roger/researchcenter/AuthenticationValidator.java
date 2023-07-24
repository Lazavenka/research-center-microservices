package com.roger.researchcenter;

import com.roger.researchcenter.exception.ApiErrorResponse;
import com.roger.researchcenter.exception.ApiErrorResponseStatus;
import com.roger.researchcenter.jwt.JwtPayloadExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationValidator implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtPayloadExtractor jwtPayloadExtractor;
    private final MessageSource messageSource;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, "Authorization header is missing in request");
            }
            final String authHeader = this.getAuthHeader(request);
            final String jwtToken = authHeader.substring(7);

            if (!authHeader.startsWith("Bearer ") || isValidToken(jwtToken)) {
                return this.onError(exchange, "Authorization header is invalid");
            }
            this.populateRequestWithHeaders(exchange, jwtToken);
        }
        return chain.filter(exchange);
    }

    private boolean isValidToken(String token) {
        try {
            jwtPayloadExtractor.verify(token);
        } catch (Exception e) {
            log.error("Token {} validation failed.", token, e);
            return false;
        }
        return true;
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        exchange.getRequest().mutate()
                .header(JwtPayloadExtractor.USERNAME_CLAIM, jwtPayloadExtractor.getUserName(token))
                .header(JwtPayloadExtractor.USER_ID_CLAIM, String.valueOf(jwtPayloadExtractor.getId(token)))
                .header(JwtPayloadExtractor.ROLES_CLAIM, String.valueOf(jwtPayloadExtractor.getRoles(token)))
                .build();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String s) {
        ServerHttpResponse response = exchange.getResponse();
        ApiErrorResponseStatus status = ApiErrorResponseStatus.BAD_AUTH_HEADER;
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(status, messageSource);
        apiErrorResponse.setOriginalErrorMessage(s);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(status.getHttpStatus());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(apiErrorResponse.toString().getBytes())));
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);

    }
}
