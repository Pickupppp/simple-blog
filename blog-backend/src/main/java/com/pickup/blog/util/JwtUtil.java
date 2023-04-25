package com.pickup.blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "blog.jwt")
@Slf4j
@Data
public class JwtUtil {

    private String secret;
    private long expire;

    public String generateToken(long userId) {
        Date now = new Date(); // 签发时间
        Date expireDate = new Date(now.getTime() + expire * 1000); // 过期时间
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("token is error", e);
            return null;
        }
    }

    public boolean isExpired(Date expireDate) {
        log.info("失效时间 {}", expireDate);
        log.info("当前时间 {}", new Date());
        return expireDate.before(new Date());
    }
}
