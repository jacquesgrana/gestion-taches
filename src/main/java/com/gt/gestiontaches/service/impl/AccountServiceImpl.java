package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Role;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.enums.UserRole;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.repository.RoleRepository;
import com.gt.gestiontaches.service.AccountService;
import com.gt.gestiontaches.service.ConfirmationService;
import com.gt.gestiontaches.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private EmployeeService employeeService;

    private ConfirmationService confirmationService;

    private RoleRepository roleRepository;

    public AccountServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, EmployeeService employeeService, ConfirmationService confirmationService, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeService = employeeService;
        this.confirmationService = confirmationService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void signup(Employee employee) throws BadRequestException {
        if (employee.getPassword() == null || employee.getPassword().length() < 8) {
            throw new BadRequestException(ErrorCode.PASSWORD_NOT_CORRECT, "Mot de passe invalide");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());
        log.info("Mot de pass en clair {} mot de passe crypté {}", employee.getPassword(), encodedPassword);
        employee.setPassword(encodedPassword);
        Role role = this.roleRepository.findByLabel(UserRole.USER).orElse(null);

        List<Role> currentRole = employee.getRoles();
        /*
        if (currentRole == null) {
            currentRole = new ArrayList<>();
        }*/
        currentRole.add(role);
        employee.setRoles(currentRole);

        employee.setEnabled(false);
        employeeService.create(employee);

        confirmationService.sendEmployeeVerificationToken(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
