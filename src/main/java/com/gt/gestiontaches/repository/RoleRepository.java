package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.Role;
import com.gt.gestiontaches.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    Optional<Role> findByLabel(UserRole label);
}
