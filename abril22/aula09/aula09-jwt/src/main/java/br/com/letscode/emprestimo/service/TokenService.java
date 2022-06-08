package br.com.letscode.emprestimo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date dateExpiration = new Date(new Date().getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("MyAPP")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(dateExpiration)
                .claim("role", user.getAuthorities().stream().findFirst().get().getAuthority())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
