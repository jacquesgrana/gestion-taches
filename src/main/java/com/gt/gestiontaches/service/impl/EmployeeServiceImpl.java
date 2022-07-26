package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.repository.EmployeeRepository;
import com.gt.gestiontaches.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> search() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void create(Employee employee) {

    }

    @Override
    public Employee read(Long id) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void taskToUser(Long taskId, Long userId) {

    }
}
