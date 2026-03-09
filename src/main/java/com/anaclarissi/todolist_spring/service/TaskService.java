package com.anaclarissi.todolist_spring.service;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.repository.TaskRepository;
import com.anaclarissi.todolist_spring.service.exceptions.ResourceNotFoundException;
import com.anaclarissi.todolist_spring.service.exceptions.TaskValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> listAll() {

        return repository.findAll();

    }

    public Task create(Task task) {

        validateDate(task);

        return repository.save(task);

    }

    public Task update(Long id,Task task) {

        try {

            validateDate(task);

            Task entity = repository.getReferenceById(id);

            updateDate(entity, task);

            return repository.save(entity);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException(id);

        }

    }

    public void delete(Long id) {

        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            throw new ResourceNotFoundException(id);

        }

    }

    private void validateDate(Task task) {

        if (task.getEndDate().isBefore(task.getStartDate())) {

            throw new TaskValidationException("The end date cannot be earlier than the start date.");

        }

        if (task.getStartDate().isBefore(LocalDate.now())) {

            throw new TaskValidationException("the start date cannot be earlier than the current date.");

        }

        if (task.getName() == null || task.getName().trim().isEmpty()) {

            throw new TaskValidationException("The name of the tariff is mandatory.");

        }

    }

    private void updateDate(Task entity, Task task) {

        entity.setName(task.getName());

        entity.setDescription(task.getDescription());

        entity.setEndDate(task.getEndDate());

        entity.setCompleted(task.isCompleted());

    }

}
