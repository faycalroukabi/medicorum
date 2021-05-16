package io.medicorum.auth.services;

import io.jsonwebtoken.Claims;

public class JwtTokenProvider {


    public static boolean validateToken(String token) {
        return true;
    }

    public Claims getClaimsFromJWT(String token) {
        return null;
    }
}
