package com.mumoo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtDecoder decoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        // Apply this to all requests
        http.antMatcher("/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(decoder);

    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webhook", "/favicon.ico");
    }
}