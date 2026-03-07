package com.anaclarissi.todolist_spring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;

    public Task () {}

    public Task(String name, String description, LocalDate startDate, LocalDate endDate, boolean completed) {

        this.name = name;

        this.description = description;

        this.startDate = startDate;

        this.endDate = endDate;

        this.completed = completed;

    }

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public LocalDate getStartDate() {

        return startDate;

    }

    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;

    }

    public LocalDate getEndDate() {

        return endDate;

    }

    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;

    }

    public boolean isCompleted() {

        return completed;

    }

    public void setCompleted(boolean completed) {

        this.completed = completed;

    }

    @Override
    public boolean equals(Object object) {

        if (object == null || getClass() != object.getClass()) return false;

        Task task = (Task) object;

        return Objects.equals(id, task.id);

    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id);

    }

}
