package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.AuthentificationData;
import com.gt.gestiontaches.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDataRepository  extends JpaRepository<AuthentificationData, Integer> {
}
