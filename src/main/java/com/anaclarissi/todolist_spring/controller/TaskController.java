package com.anaclarissi.todolist_spring.controller;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getAll() {

        return  service.listAll();

    }

    @PostMapping
    public Task save(@RequestBody Task task) {

        return  service.create(task);

    }

    @DeleteMapping
    public void remove(@PathVariable Long id) {

        service.delete(id);

    }

}
