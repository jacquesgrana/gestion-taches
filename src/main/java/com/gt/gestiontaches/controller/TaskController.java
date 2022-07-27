package com.gt.gestiontaches.controller;

import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path="all")
    public List<Task> search() {
        return this.taskService.search();
    }

    @GetMapping(path = "{id}")
    public Task read(@PathVariable("id") Long id) {
        return this.taskService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Task task) {
        this.taskService.create(task);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        this.taskService.delete(id);
    }

    @PutMapping(path = "{id}")
    public Task update(@RequestBody Task task, @PathVariable("id") Long id) {
        return this.taskService.update(task, id);
    }
}
