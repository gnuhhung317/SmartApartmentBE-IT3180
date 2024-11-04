package com.hust.smart_apartment.security.jwt;

import com.hust.smart_apartment.constants.Role;
import com.hust.smart_apartment.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JWTProvider {

    @Value("${application.security.authentication.jwt.secret}")
    private String secret;
    @Value("${application.security.authentication.jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;

    public String generateToken(User user) {
        long now = new Date().getTime();

        Date validity = new Date(now + tokenValidityInSeconds * 1000);

        List<?> authorities = user.getRole() != null
                ? Stream.of(user.getRole())
                .map(role -> new SimpleGrantedAuthority(role.name())).toList()
                : List.of();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", authorities)
                .claim("userId", user.getUserId())
                .claim("fullName", user.getFullName())
                .claim("residentId", user.getResidentId())
                .signWith(getSignInKey(),Jwts.SIG.HS256)
                .expiration(validity)
                .issuedAt(new Date(now))
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
            return claims.getExpiration().getTime()-new Date().getTime()> 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        User user = new User();
        user.setUsername(claims.getSubject());
        String role = (String) ((LinkedHashMap) claims.get("role", List.class).getLast()).get("authority");
        user.setRole(Role.getRole(role));
        user.setUserId(claims.get("userId", Long.class));
        user.setFullName(claims.get("fullName", String.class));
        user.setResidentId(claims.get("residentId", Long.class));
        return new UsernamePasswordAuthenticationToken(user, token, List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
