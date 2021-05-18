package io.medicorum.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.medicorum.auth.configuration.JwtConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.*;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtTokenProvider {

    private static  JwtConfiguration jwtConfiguration;

    public JwtTokenProvider(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }


    public static boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(jwtConfiguration.getSecret().getBytes()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Claims getClaimsFromJWT(String token) {

        return Jwts.parser().setSigningKey(jwtConfiguration.getSecret().getBytes()).parseClaimsJws(token).getBody();
    }

    public String generateToken(Authentication authentication) {

        Long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfiguration.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecret().getBytes())
                .compact();
    }
}
