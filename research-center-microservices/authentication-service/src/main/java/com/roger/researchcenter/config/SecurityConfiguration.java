package com.roger.researchcenter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService service;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.service);
        authProvider.setPasswordEncoder(setPasswordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder setPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.POST,"/api/v1/auth/sign-in").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/get_users").permitAll()
                                //.requestMatchers(HttpMethod.POST, "/api/v1/equipment").hasRole(UserRole.MANAGER.getRoleString())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/manager").hasRole("MANAGER")
                                //.requestMatchers(HttpMethod.POST, "/api/v1/departments").hasRole(UserRole.ADMIN.getRoleString())
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/admin").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/test/user").hasRole("USER")
                                .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(this.service)
                .authenticationProvider(authenticationProvider());

        return http.build();
    }
}
