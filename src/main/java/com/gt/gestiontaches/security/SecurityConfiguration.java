package com.gt.gestiontaches.security;

import com.gt.gestiontaches.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AccountService accountService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(
                (requests)->
                        requests.antMatchers(POST, "signin").permitAll()
                                .antMatchers(POST, "signup").permitAll()
                                .antMatchers(POST, "activate").permitAll()
                                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer () {
        return (web) -> web.ignoring()
                .antMatchers(POST, "/signin")
                .antMatchers(POST, "/signup")
                .antMatchers(POST, "/activate");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authentificationConfiguartion) throws Exception {
            return authentificationConfiguartion.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

}
