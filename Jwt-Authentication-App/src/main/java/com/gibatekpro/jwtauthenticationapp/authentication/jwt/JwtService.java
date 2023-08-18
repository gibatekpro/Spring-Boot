package com.gibatekpro.jwtauthenticationapp.authentication.jwt;


import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


public interface JwtService {

//    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
//    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
//    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
//    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
//    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
//    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'

    String issueToken(User user);

    //Generate token without extra claims
    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    );

    String extractUserEmailFromToken(String jwtToken);

    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    Claims extractAllClaims(String token);

    Key getSigningKey();

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Date extractTokenExpiration(String token);

}
