package Sloppify.Security;

import Sloppify.User.SloppifyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(SloppifyUser sloppifyUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("sloppify")
                    .withSubject(sloppifyUser.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("role", sloppifyUser.getSloppifyUserRole().toString())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public Map<String, String> validateToken(String token) {
        HashMap<String, String> claims = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifiedToken = JWT.require(algorithm).withIssuer("sloppify").build().verify(token);
            claims.put("sub", verifiedToken.getSubject());
            claims.put("role", verifiedToken.getClaim("role").asString());
            return claims;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while trying to validate token", e);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+0"));
    }

}
