package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.service.AccountService;
import com.gt.gestiontaches.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;

    @Override
    public void signup(Employee employee) throws BadRequestException {
        if (employee.getPassword() != null && employee.getPassword().length() < 8) {
            throw new BadRequestException(ErrorCode.USERNAME_ALREADY_EXISTS, "pas de pw");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());
        log.info("Mot de pass en clait {} mot de passe crypté {}", employee.getPassword(), encodedPassword);
        employee.setPassword(encodedPassword);
        employeeService.create(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
