package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.dto.AuthentificationDTO;
import com.gt.gestiontaches.dto.TokenDTO;
import com.gt.gestiontaches.entity.ConfirmationToken;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Role;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.enums.UserRole;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.repository.RoleRepository;
import com.gt.gestiontaches.security.JWTTokenUtils;
import com.gt.gestiontaches.service.AccountService;
import com.gt.gestiontaches.service.AuthDataService;
import com.gt.gestiontaches.service.ConfirmationService;
import com.gt.gestiontaches.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;
    private ConfirmationService confirmationService;

    private JWTTokenUtils jwtTokenUtils;

    private AuthDataService authDataService;

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
    @Transactional
    @Override
    public void activate(String username, String token) throws BadRequestException {

        ConfirmationToken confirmationToken = this.confirmationService.getEmployeeVerificationToken(username,  token);
        Employee employee = this.employeeService.getByUserName(username);

        if(confirmationToken.getEmployee().getId() != employee.getId()){
            throw new UsernameNotFoundException ("Utilisateur inconnu");
        }
        confirmationToken.setActivation(Instant.now());
        employee.setEnabled(true);

    }

    @Override
    public TokenDTO generateTokens(AuthentificationDTO authentificationDTO) throws BadRequestException {
        Employee employee = this.employeeService.getByUserName(authentificationDTO.getUsername());
        String authentificationToken = this.jwtTokenUtils.generateToken(employee);
        String refreshToken = RandomStringUtils.random(40, true, true);
        TokenDTO tokens = new TokenDTO();

        // stocker objet authdata (tokendto et employee)


        tokens.setAuthentification(authentificationToken);
        tokens.setRefresh(refreshToken);

        authDataService.create(tokens, employee);
        return tokens;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return this.employeeService.getByUserName(username);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
