package com.roger.scheduleservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        return messageSource;
    }
}
