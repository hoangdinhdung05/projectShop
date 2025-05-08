package hoangdung.vn.shop.services;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import hoangdung.vn.shop.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private final JwtConfig jwtConfig;
    private final Key key;

    public JwtService(JwtConfig jwtConfig, Key key) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(jwtConfig.getSecretKey().getBytes()));
    }

    //genarate token
    //Lấy thông tin từ userId và username (email)
    public String generateToken(Long userId, String email) {
        Date now = new Date();
        //expirate
        Date expiration = new Date(now.getTime() + jwtConfig.getExpirationTime());
        //build token
        return Jwts.builder()
                 .setSubject(String.valueOf(userId))
                 .claim("email", email)
                 .setIssuedAt(now)
                 .setExpiration(expiration)
                 .signWith(key, SignatureAlgorithm.HS256)
                 .compact();
    }

    //Lấy userId từ token
    public String getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
