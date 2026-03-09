package com.anaclarissi.todolist_spring.controller;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {

        List<Task> list = service.listAll();

        return ResponseEntity.ok().body(list);

    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task task) {

        task = service.create(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {

        task = service.update(id, task);

        return ResponseEntity.ok().body(task);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
