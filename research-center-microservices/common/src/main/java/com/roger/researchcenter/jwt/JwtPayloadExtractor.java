package com.roger.researchcenter.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

@Slf4j
public class JwtPayloadExtractor {
    private static final String JWT_PROPERTIES = "jwt.properties";
    private static final String JWT_SECRET_PROPERTY = "jwt.secret";
    private static final String JWT_SECRET_DEFAULT = "kF:HLKhLKFHIUAEYB&*#VYyLOI UBVOI#YB";
    private static final String JWT_LIFETIME_PROPERTY = "jwt.lifetime";
    private static final Duration JWT_LIFETIME_DEFAULT = Duration.of(30L, ChronoUnit.MINUTES);

    private final String jwtSecret;
    private final Duration jwtLifeTime;

    public static final String ROLES_CLAIM = "roles";
    public static final String USER_ID_CLAIM = "userId";


    public JwtPayloadExtractor() {
        Duration jwtLifeTimeTemp;
        Properties properties = new Properties();
        try (InputStream inputStream = JwtPayloadExtractor.class.getClassLoader()
                .getResourceAsStream(JWT_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Cannot load Jwt properties.", e);
            log.error("File not found. Start loading defaults...");
        }
        jwtSecret = properties.containsKey(JWT_SECRET_PROPERTY) ?
                properties.getProperty(JWT_SECRET_PROPERTY) : JWT_SECRET_DEFAULT;
        if (properties.containsKey(JWT_LIFETIME_PROPERTY)) {
            try {
                jwtLifeTimeTemp = Duration.parse(properties.getProperty(JWT_LIFETIME_PROPERTY));
            } catch (DateTimeParseException e) {
                log.error("Error during parsing Jwt lifetime property. Load defaults.", e);
                jwtLifeTimeTemp = JWT_LIFETIME_DEFAULT;
            }
        } else {
            jwtLifeTimeTemp = JWT_LIFETIME_DEFAULT;
        }
        jwtLifeTime = jwtLifeTimeTemp;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Duration getJwtLifeTime() {
        return jwtLifeTime;
    }

    public String getUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public List<String> getRoles(String token) {
        return extractClaim(token, claims -> claims.get(ROLES_CLAIM, List.class));
    }

    public Long getId(String token) {
        return extractClaim(token, claims -> claims.get(USER_ID_CLAIM, Long.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public void verify(String token) {
        getAllClaimsFromToken(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody();
    }

}
