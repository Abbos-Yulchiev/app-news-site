package uz.pdp.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;

import java.util.Date;

@Component
public class JwtProvider {

    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "secretKeyToKeepSecret";

    public String generateToken(String username, Role roles) {

        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", roles.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUserFromToken(String token) {

        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
