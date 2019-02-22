package com.mumoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        // Apply this to all requests
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(
                        "/webhook"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
//                .and()
//                // Logout config
////                .logout()
////                // Workaround to support logout with both GET and POST (CSRF)
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                // On successful logout go to home URL
//                .logoutSuccessUrl("/index.html")
//                .and()
//                    .oauth2Login();
        // @formatter:on
    }

}