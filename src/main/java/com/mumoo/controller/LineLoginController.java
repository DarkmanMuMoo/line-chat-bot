package com.mumoo.controller;

import com.mumoo.model.User;
import com.mumoo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineLoginController {


     private AuthenticationService authenticationService;

   @Autowired
    public LineLoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/")
    public OAuth2User index(
                        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
       String lineId = (String) oauth2User.getAttributes().get("userId");
       User user = authenticationService.getOrCreateUserFromLineID(lineId).block();

        return oauth2User;
    }
}