package simbirsoft.com.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simbirsoft.com.authservice.domain.Role;
import simbirsoft.com.authservice.domain.User;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final String secret = "SECRET_KEY";
    private final Duration duration = Duration.ofSeconds(1200, 0);

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();
        claims.put("role", roles);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + duration.getSeconds());

        Map<String, Object> payload = new HashMap<>();
        payload.put("first_name", user.getFirstName());
        payload.put("last_name", user.getLastName());
        payload.put("phone_number", user.getPhoneNumber());

        return JWT.create()
                .withSubject(user.getEmail())
                .withPayload(payload)
                .withClaim("roles", claims)
                .withExpiresAt(expiredDate)
                .sign(Algorithm.HMAC256(secret));
    }

}
