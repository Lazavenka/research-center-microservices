package com.roger.researchcenter.token;

import com.roger.researchcenter.jwt.JwtPayloadExtractor;
import com.roger.researchcenter.model.UserCredentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private final String jwtSecret;

    private final Duration jwtLifeTime;

    private final JwtPayloadExtractor jwtPayloadExtractor;

    public JwtTokenUtils(){
        jwtPayloadExtractor = new JwtPayloadExtractor();
        this.jwtSecret = jwtPayloadExtractor.getJwtSecret();
        this.jwtLifeTime = jwtPayloadExtractor.getJwtLifeTime();
    }
    public String generateJwtToken(UserCredentials credentials) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = credentials.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put(JwtPayloadExtractor.ROLES_CLAIM, rolesList);
        claims.put(JwtPayloadExtractor.USER_ID_CLAIM, credentials.getId());
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifeTime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(credentials.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }

    public String getUserName(String token) {
        return jwtPayloadExtractor.getUserName(token);
    }

    public List<String> getRoles(String token) {
        return jwtPayloadExtractor.getRoles(token);
    }

    public Long getId(String token) {
        return jwtPayloadExtractor.getId(token);
    }

    public void verify(String token) {
        jwtPayloadExtractor.verify(token);
    }
}
