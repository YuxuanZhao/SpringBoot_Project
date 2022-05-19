package com.example.demo.task;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long task_id) {
        return taskRepository.findTaskById(task_id);
    }

    public Task addNewTask(Task task){
        Optional<Task> taskOptional = taskRepository.findTaskByName(task.getName());
        if (taskOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException("task with id " + taskId + " does not exist");
        }
        taskRepository.deleteById(taskId);

    }

    @Transactional
    public Task updateTask(Long taskId, String name, String description, boolean reminder) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalStateException("student with id " + taskId + " does not exist"));
        if (description != null && description.length() > 0 && !Objects.equals(task.getDescription(), description)) {
            task.setDescription(description);
        }
        if (name != null && name.length() > 0 && !Objects.equals(task.getName(), name)) {
            Optional<Task> studentOptional = taskRepository.findTaskByName(name);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("name taken");
            }
            task.setName(name);
        }
        if (!Objects.equals(task.getReminder(), reminder)) {
            task.setReminder(reminder);
        }
        return task;
    }
}
