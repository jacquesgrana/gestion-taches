package com.gt.gestiontaches.service;

import com.gt.gestiontaches.dto.TokenDTO;
import com.gt.gestiontaches.entity.Employee;

public interface AuthDataService {

    void create(TokenDTO tokenDTO, Employee employee);
}
