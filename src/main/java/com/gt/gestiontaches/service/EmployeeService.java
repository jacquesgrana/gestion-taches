package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;

import java.util.List;

public interface EmployeeService {

    List<Employee> search(String query);
    void create (Employee employee) throws BadRequestException;
    Employee read(Integer id) throws BadRequestException;
    Employee getByUserName(String username) throws BadRequestException;
    Employee update(Employee employee, Integer id) throws BadRequestException;
    void delete(Integer id) throws BadRequestException;

    void taskToEmployee(Integer taskId, Integer employeeId) throws BadRequestException;
}
