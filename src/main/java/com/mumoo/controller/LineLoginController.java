package com.mumoo.controller;

import com.mumoo.model.Token;
import com.mumoo.model.User;
import com.mumoo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;

@RestController
public class LineLoginController {


     private AuthenticationService authenticationService;

   @Autowired
    public LineLoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // this still not met standard
    @GetMapping(value = "/line-login")
    public Mono<Token> index(
                        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) throws NoSuchAlgorithmException, JOSEException {
       String lineId = (String) oauth2User.getAttributes().get("userId");
       User user = authenticationService.getOrCreateUserFromLineID(lineId).block();
       return authenticationService.login(user.getId());
    }
}