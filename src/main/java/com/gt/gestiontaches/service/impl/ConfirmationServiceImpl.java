package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.ConfirmationToken;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.repository.ConfirmationTokenRepository;
import com.gt.gestiontaches.service.ConfirmationService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@AllArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {

    private ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public void sendEmployeeVerificationToken(Employee employee) {
        String validationToken = RandomStringUtils.random(6, false, true);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setValue(validationToken);
        confirmationToken.setEmployee(employee);
        confirmationToken.setCreation(Instant.now());
        this.confirmationTokenRepository.save(confirmationToken);
    }
}
