package com.roger.researchcenter.config;

import com.roger.researchcenter.token.JwtAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationConfigurer extends CustomConfigurer {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public RestAuthenticationConfigurer(JwtAuthenticationFilter filter, AccessDeniedHandler accessDeniedHandler, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtAuthenticationFilter = filter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/sign-in").permitAll()
                                .requestMatchers(HttpMethod.GET, "/error").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/get_users").permitAll()
                                //.requestMatchers(HttpMethod.POST, "/api/v1/equipment").hasRole(UserRole.MANAGER.getRoleString())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/manager").hasAnyRole("MANAGER", "ADMIN")
                                //.requestMatchers(HttpMethod.POST, "/api/v1/departments").hasRole(UserRole.ADMIN.getRoleString())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/admin").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/user").hasAnyRole("USER", "MANAGER", "ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(configurer -> configurer.accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint));

    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public RestAuthenticationConfigurer jwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        return this;
    }

    public RestAuthenticationConfigurer accessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
        return this;
    }

}
