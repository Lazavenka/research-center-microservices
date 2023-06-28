package com.roger.researchcenter.config;

import com.roger.researchcenter.repository.UserRepository;
import com.roger.researchcenter.service.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findUserByEmail(username)
                .map(UserCredentials::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + "not found!"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationProvider provider,
                                                   RestAuthenticationConfigurer configurer) throws Exception {
        http
                .authenticationProvider(provider)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .apply(configurer);
        return http.build();
    }
}
