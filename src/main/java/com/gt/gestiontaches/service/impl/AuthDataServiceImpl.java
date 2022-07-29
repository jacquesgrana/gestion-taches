package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.dto.TokenDTO;
import com.gt.gestiontaches.entity.AuthentificationData;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.repository.AuthDataRepository;
import com.gt.gestiontaches.service.AuthDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthDataServiceImpl implements AuthDataService {

    private AuthDataRepository authDataRepository;

    @Transactional
    @Override
    public void create(TokenDTO tokenDTO, Employee employee) {
        AuthentificationData authData = new AuthentificationData();
        authData.setAuthToken(tokenDTO.getAuthentification());
        authData.setRefreshToken(tokenDTO.getRefresh());
        authData.setEmployee(employee);
        authDataRepository.save(authData);
    }
}
