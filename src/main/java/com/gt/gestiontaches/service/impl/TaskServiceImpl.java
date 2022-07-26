package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.repository.TaskRepository;
import com.gt.gestiontaches.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl() {
    }

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
        task.setRt(task.getIt());
        this.taskRepository.save(task);
    }

    @Override
    public Task read(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }


}
