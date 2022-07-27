package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.repository.TaskRepository;
import com.gt.gestiontaches.service.EmployeeService;
import com.gt.gestiontaches.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //@Autowired
    //private EmployeeService employeeService;

    public TaskServiceImpl() {
    }

    /*
    public TaskServiceImpl(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }*/

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> search() {
        return this.taskRepository.findAll();
    }

    @Override
    public void create(Task task) {
        // verif TI > TR
        // verif TR =
        log.info("Création de la tache {}", task.getTitle());
        task.setRt(task.getIt());
        this.taskRepository.save(task);
    }

    @Override
    public Task read(Integer id) throws BadRequestException {
        return this.taskRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorCode.TASK_NOT_FOUND, "Pas de tache à cet id"));
    }

    @Override
    public Task update(Task task, Integer id) throws BadRequestException {
        Task current = this.read(id);
        current.setTitle(task.getTitle());
        current.setDescription(task.getDescription());
        current.setIt(task.getIt());
        current.setRt(task.getRt());
        taskRepository.save(current); // *******************************************************
        return current;
    }

    @Override
    public void delete(Integer id) {
        this.taskRepository.deleteById(id);
    }


    @Override
    public void employeeToTask(Integer employeeId, Integer taskId) {

    }


}
