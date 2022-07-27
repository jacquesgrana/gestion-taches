package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.exceptions.BadRequestException;

import java.util.List;

public interface TaskService {

    List<Task> search();
    void create (Task task);
    Task read(Integer id) throws BadRequestException;
    Task update(Task task, Integer id) throws BadRequestException;
    void delete(Integer id) throws BadRequestException;

    void employeeToTask(Integer userId, Integer taskId);
}
