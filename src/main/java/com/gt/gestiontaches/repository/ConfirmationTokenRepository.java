package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

    Optional<ConfirmationToken> findByValueAndEmployeeUserName(String value, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeUserNameAndActivationNull(String token, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeId(String value, int id);
}
