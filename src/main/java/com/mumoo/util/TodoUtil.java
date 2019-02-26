package com.mumoo.util;


import com.mumoo.model.Task;
import com.mumoo.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TodoUtil {
    public static final String DEFAULT_TIME = "12:00";
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yy");

    public static Task parseMessage(String message, Long userId) {

        String[] chunk = message.split(" : ");

        String taskName = chunk[0].trim();
        String day = chunk[1].trim();
        String time = chunk.length == 2 ? DEFAULT_TIME : chunk[2].trim();


        return new Task(null, taskName, parseDate(day, time), false, false, userId);

    }

    private static LocalDateTime parseDate(String day, String time) {

        if ("tomorrow".equals(day)) {
            return LocalDateTime.of(LocalDate.now().plusDays(1L), LocalTime.parse(time, timeFormatter));
        } else if ("today".equals(day)) {
            return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time, timeFormatter));
        } else {
            return LocalDateTime.of(LocalDate.parse(day, dateTimeFormatter), LocalTime.parse(time, timeFormatter));
        }
    }


    public static void authorizeTask(Optional<Task> taskOptional, User user) throws Exception {

        if (taskOptional.isEmpty()) {
            throw new Exception("user not found");
        }
        Task task = taskOptional.get();

        if (!task.getId().equals(user.getId())) {
            throw new Exception("un authorize");
        }

    }


    public static SignedJWT createJwtToken(JWTClaimsSet claimsSet, String secret) throws NoSuchAlgorithmException, JOSEException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] sharedSecret = digest.digest(
                secret.getBytes(StandardCharsets.UTF_8));
        JWSSigner signer = new MACSigner(sharedSecret);
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);


        return signedJWT;
    }


    public static SignedJWT parseAndVerifyJwtToken(String token, String secret) throws NoSuchAlgorithmException, JOSEException, ParseException {


        SignedJWT signedJWT = SignedJWT.parse(token);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] sharedSecret = digest.digest(
                "mumoo".getBytes(StandardCharsets.UTF_8));
        JWSVerifier verifier = new MACVerifier(sharedSecret);
        signedJWT.verify(verifier);

        return signedJWT;

    }

}
