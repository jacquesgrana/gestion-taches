package com.gt.gestiontaches.controller;

import com.gt.gestiontaches.dto.ActivationDTO;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AccountController {

    private AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    void signup(@RequestBody Employee employee) throws BadRequestException {
        this.accountService.signup(employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "activate", consumes = MediaType.APPLICATION_JSON_VALUE)
    void activate(@RequestBody ActivationDTO activationDto) throws BadRequestException {
        this.accountService.activate(activationDto.getUsername(), activationDto.getToken());
    }
}
