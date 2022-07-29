package com.gt.gestiontaches.security;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.service.AccountService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    AccountService accountService;
    JWTTokenUtils jwtTokenUtils;
    String authorisation;

    public JWTFilter(AccountService accountService, JWTTokenUtils jwtTokenUtils, String authorisation) {
        super();
        this.accountService = accountService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authorisation = authorisation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(authorisation);
        if(header != null && header.startsWith("Bearer ")) {
            // TODO chercher token dans bdd
            String token = header.substring(7);
            String username = this.jwtTokenUtils.getUserNameFromToken(token);

            if(username != null) {
                UserDetails employee = this.accountService.loadUserByUsername(username);
                Boolean isTokenValid = jwtTokenUtils.isTokenValid(token, employee);

                if(isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(employee, employee.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
