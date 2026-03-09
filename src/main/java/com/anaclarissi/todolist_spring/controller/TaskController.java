package com.anaclarissi.todolist_spring.controller;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {

        task = service.update(id, task);

        return ResponseEntity.ok().body(task);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
