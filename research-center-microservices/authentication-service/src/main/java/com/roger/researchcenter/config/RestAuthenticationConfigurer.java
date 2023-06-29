package com.roger.researchcenter.config;

import com.roger.researchcenter.model.Roles;
import com.roger.researchcenter.token.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestAuthenticationConfigurer extends CustomConfigurer {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;


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
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/manager").hasAnyRole(Roles.MANAGER.getValue(), Roles.ADMIN.getValue())
                                //.requestMatchers(HttpMethod.POST, "/api/v1/departments").hasRole(UserRole.ADMIN.getRoleString())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/admin").hasRole(Roles.ADMIN.getValue())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/user").hasAnyRole(Roles.USER.getValue(), Roles.MANAGER.getValue(), Roles.ADMIN.getValue())
                                .anyRequest().authenticated())
                .exceptionHandling(configurer -> configurer.accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint));

    }

    @Override
    public void configure(HttpSecurity builder){
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

    public RestAuthenticationConfigurer authenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        return this;
    }
}
