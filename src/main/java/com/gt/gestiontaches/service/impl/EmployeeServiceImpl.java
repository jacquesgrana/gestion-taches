package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.repository.EmployeeRepository;
import com.gt.gestiontaches.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
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
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee read(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee update(Employee employee, Long id) {
        Employee current = this.read(id);
        current.setFirstName(employee.getFirstName());
        current.setLastName(employee.getLastName());
        //employeeRepository.save(current);
        return current;
    }

    @Override
    public void delete(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void taskToUser(Long taskId, Long userId) {

    }
}
