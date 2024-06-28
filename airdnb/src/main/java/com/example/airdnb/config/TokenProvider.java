package com.example.airdnb.config;

import com.example.airdnb.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenProvider {

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret-key}")
    private String signatureSecretKey;
    @Value("${jwt.expiration}")
    private Long expirationTime;
    private SecretKey key;

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(signatureSecretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        return Jwts.builder()
            .issuer(issuer)
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + expirationTime))
            .subject(user.getEmail())
            .claim("id", user.getId())
            .claim("name", user.getName())
            .claim("role", user.getKey())
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            log.debug("검증 성공");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("검증 실패");
            return false;
        }
    }

    public Authentication getAuthentication(String token, UserDetails userDetails) {
        Claims claims = getClaims(token);
        Set<GrantedAuthority> authorities = Collections.singleton(
            new SimpleGrantedAuthority((String) claims.get("role")));

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();

    }

}
