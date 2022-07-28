package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserName(String userName);

    @Query("SELECT e FROM Employee e WHERE e.userName LIKE CONCAT('%', :username, '%')")
    Stream<Employee> search(String username);
}
