package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    void signup(Employee employee) throws BadRequestException;

    void activate(String username, String token) throws BadRequestException;
}
