package com.gibatekpro.jwtauthenticationapp.authentication.jwt;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{


    @Value("${secret.key}")
    private String SECRET_KEY;

    @Override
    public String issueToken(User user) {
        return generateToken(user);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUserEmailFromToken(String jwtToken) {

        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    @Override
    public Claims extractAllClaims(String token) {

        return (Claims) Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parse(token)
                .getBody();
    }

    @Override
    public Key getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

        //Extract the username/email from the jwtToken
        final String userEmail = extractUserEmailFromToken(jwtToken);

        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    @Override
    public boolean isTokenExpired(String token) {

        return extractTokenExpiration(token).before(new Date());
    }

    @Override
    public Date extractTokenExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

}
