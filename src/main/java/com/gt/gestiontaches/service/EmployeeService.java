package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> search();
    void create (Employee employee);
    Employee read(Long id);
    Employee update(Employee employee, Long id);
    void delete(Long id);

    void taskToEmployee(Long taskId, Long userId);
}
