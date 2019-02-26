package com.mumoo.service;

import com.mumoo.model.Token;
import com.mumoo.model.User;
import com.mumoo.repository.TokenRepository;
import com.mumoo.repository.UserRepository;
import com.mumoo.util.TodoUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class AuthenticationService {

    private TokenRepository tokenRepository;
    private UserRepository userRepository;

    private String secret = "mumoo";

    @Autowired
    public AuthenticationService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }



    public Mono<User> getOrCreateUserFromLineID(String lineId){

        Optional<User> user =   this.userRepository.getByLineId(lineId);
        if(user.isEmpty()){
            return   Mono.just(this.userRepository.save(new User(lineId, LocalDateTime.now())));
        }

        return  Mono.just(user.get());

    }

    public Mono<Token> login(Long userId) throws NoSuchAlgorithmException, JOSEException {
        LocalDateTime created  = LocalDateTime.now();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId.toString())
                .issuer("https://mumoo.com")
                .expirationTime(Date.from(created.plusDays(7).toInstant(ZoneOffset.UTC)))
                .audience("todo")
                .issueTime(Date.from(created.toInstant(ZoneOffset.UTC)))
                .build();
        SignedJWT jwt  = TodoUtil.createJwtToken( claimsSet,this.secret);
        Token token = new Token(jwt.serialize(),created,created.plusDays(7),userId);
       token.setNewEntity(true);
        return Mono.just(tokenRepository.save(token));

    }




}
