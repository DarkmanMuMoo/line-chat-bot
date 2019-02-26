package com.mumoo.service;

import com.mumoo.repository.TokenRepository;
import com.mumoo.util.TodoUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class JwtTokenDecoder implements JwtDecoder {

    private TokenRepository tokenRepository;
    private String secret = "mumoo";

    @Autowired
    public JwtTokenDecoder(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
            try {

               boolean isExist =  tokenRepository.existsById(token);
               if(!isExist){
                   throw new JwtException("token not exist");
               }
                SignedJWT signedJWT = TodoUtil.parseAndVerifyJwtToken(token,this.secret);
                JWTClaimsSet claim = signedJWT.getJWTClaimsSet();
                Map<String, Object> headers = new LinkedHashMap<>(signedJWT.getHeader().toJSONObject());
                Jwt springJwt = new Jwt(signedJWT.serialize(), claim.getIssueTime().toInstant(), claim.getExpirationTime().toInstant(), headers, claim.getClaims());
                return springJwt;
            } catch (ParseException | JOSEException | NoSuchAlgorithmException e) {
                throw new JwtException(e.getMessage());
            }
        }
    }

