package com.mumoo.config;

import com.mumoo.util.TodoUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private static Jwt decode(String token) {
        try {

            SignedJWT signedJWT = TodoUtil.parseAndVerifyJwtToken(token,"mumoo");
            JWTClaimsSet claim = signedJWT.getJWTClaimsSet();
            Map<String, Object> headers = new LinkedHashMap<>(signedJWT.getHeader().toJSONObject());
            Jwt springJwt = new Jwt(signedJWT.serialize(), claim.getIssueTime().toInstant(), claim.getExpirationTime().toInstant(), headers, claim.getClaims());
            return springJwt;
        } catch (ParseException | JOSEException | NoSuchAlgorithmException e) {
            throw new JwtException(e.getMessage());
        }
    }

    //private String key = "mumoo";

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
                .oauth2Login()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(WebSecurity::decode);

    }

}