package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.repository.EmployeeRepository;
import com.gt.gestiontaches.repository.TaskRepository;
import com.gt.gestiontaches.service.EmployeeService;
import com.gt.gestiontaches.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskService taskService;

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    @Override
    public List<Employee> search() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void create(Employee employee) throws BadRequestException {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUserName(employee.getUserName());
        if(employeeOptional.isPresent()) {
            throw new BadRequestException(ErrorCode.USERNAME_ALREADY_EXISTS, "Un employé existe déjà avec cet username");
        }
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
        employeeRepository.save(current); // *****************************************
        return current;
    }

    @Override
    public void delete(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void taskToEmployee(Long taskId, Long employeeId) {
        Employee currentEmpl = this.read(employeeId);
        Task currentTask = taskService.read(taskId);
        currentEmpl.getTasks().add(currentTask);
        //currentTask.getEmployees().add(currentEmpl);
        employeeRepository.save(currentEmpl); // ***********************************************
    }
}
