package com.example.demo.task;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private LocalDate ddl;
    private boolean reminder;
    @Transient
    private Integer restDay;

    public Task() {

    }

    public Task(String name, String description, LocalDate ddl, boolean reminder) {
        this.name = name;
        this.description = description;
        this.ddl = ddl;
        this.reminder = reminder;
    }

    public Task(Long id, String name, String description, LocalDate ddl, boolean reminder) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ddl = ddl;
        this.reminder = reminder;
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

    public LocalDate getDdl() {
        return ddl;
    }

    public void setDdl(LocalDate ddl) {
        this.ddl = ddl;
    }

    public Integer getRestDay() {
        return Period.between(LocalDate.now(), this.ddl).getDays();
    }

    public void setRestDay(Integer restDay) {
        this.restDay = restDay;
    }

    public boolean getReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ddl=" + ddl +
                ", restDay=" + restDay +
                '}';
    }
}
