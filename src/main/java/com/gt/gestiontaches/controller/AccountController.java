package com.gt.gestiontaches.controller;

import com.gt.gestiontaches.dto.ActivationDTO;
import com.gt.gestiontaches.dto.AuthentificationDTO;
import com.gt.gestiontaches.dto.TokenDTO;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.service.AccountService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AccountController {

    private AuthenticationManager authentificationManager;
    private AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody Employee employee) throws BadRequestException {
        this.accountService.signup(employee);
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TokenDTO signin(@RequestBody AuthentificationDTO authentificationDTO) throws BadRequestException {
       this.authentificationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       authentificationDTO.getUsername(),
                       authentificationDTO.getPassword()
               )
       );
       TokenDTO tokenDTO = this.accountService.generateTokens(authentificationDTO);
       return tokenDTO;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "activate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestBody ActivationDTO activationDto) throws BadRequestException {
        this.accountService.activate(activationDto.getUsername(), activationDto.getToken());
    }


}
