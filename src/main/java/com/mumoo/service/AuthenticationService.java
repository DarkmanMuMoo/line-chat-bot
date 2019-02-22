package com.mumoo.service;

import com.mumoo.model.Token;
import com.mumoo.model.User;
import com.mumoo.repository.TokenRepository;
import com.mumoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationService {

    private TokenRepository tokenRepository;
    private UserRepository userRepository;

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

    public Mono<Token> login(Long userId){


        Token token = new Token();

        return Mono.just(tokenRepository.save(token));



    }


}
