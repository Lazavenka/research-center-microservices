package com.roger.researchcenter.config;

import com.roger.researchcenter.exception.JwtVerifyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtVerifyInterceptor jwtVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtVerifyInterceptor)
                .excludePathPatterns("/api/v1/test/get_users",
                        "/api/v1/auth/login",
                        "/api/v1/auth/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
