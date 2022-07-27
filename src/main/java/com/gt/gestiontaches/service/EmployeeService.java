package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;

import java.util.List;

public interface EmployeeService {

    List<Employee> search();
    void create (Employee employee) throws BadRequestException;
    Employee read(Long id) throws BadRequestException;
    Employee update(Employee employee, Long id) throws BadRequestException;
    void delete(Long id);

    void taskToEmployee(Long taskId, Long employeeId) throws BadRequestException;
}
