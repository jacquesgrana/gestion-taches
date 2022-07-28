package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.ConfirmationToken;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;

public interface ConfirmationService {

    void sendEmployeeVerificationToken(Employee employee);

    ConfirmationToken getEmployeeVerificationToken(String username, String token) throws BadRequestException;
}
