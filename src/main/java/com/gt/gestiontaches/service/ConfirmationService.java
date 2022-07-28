package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Employee;

public interface ConfirmationService {

    void sendEmployeeVerificationToken(Employee employee);
}
