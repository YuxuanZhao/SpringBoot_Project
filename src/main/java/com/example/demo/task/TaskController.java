package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping(path = "{taskId}")
	public Task getTask(@PathVariable("taskId") Long taskId) {
		return taskService.getTask(taskId);
	}

	@GetMapping
	public List<Task> getTasks() {
		return taskService.getTasks();
	}

	@PostMapping
	public Task registerNewTask(@RequestBody Task task){
		return taskService.addNewTask(task);
	}

	@DeleteMapping(path = "{taskId}")
	public void deleteTask(@PathVariable("taskId") Long taskId){
		taskService.deleteTask(taskId);
	}

	@PutMapping(path = "{taskId}")
	public Task updateTask(@PathVariable("taskId") Long taskId,
							  @RequestParam(required = false) String name,
							  @RequestParam(required = false) String description,
						   @RequestParam(required = false) boolean reminder) {
		return taskService.updateTask(taskId, name, description, reminder);
	}
}
