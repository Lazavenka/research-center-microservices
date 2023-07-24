package com.roger.researchcenter.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public abstract class CustomConfigurer extends AbstractHttpConfigurer<CustomConfigurer, HttpSecurity> {
}
