package com.gt.gestiontaches.security;

import com.gt.gestiontaches.entity.Employee;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTTokenUtils {

    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", employee.getFirstName());
        claims.put("lastName", employee.getLastName());
        claims.put("roles", employee.getRoles());
        claims.put("userName", employee.getUsername());
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, 2);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(employee.getUsername())
                .setIssuer("GESTION_TACHES")
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .compact();
        log.info("TOKEN {}", token);
        return token;
    }
}
