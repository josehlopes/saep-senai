package com.example.saep.controller;

import com.example.saep.dto.TaskDTO;
import com.example.saep.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public void create(@RequestBody TaskDTO taskDTO) {
        taskService.create(taskDTO);
    }

    @GetMapping("/")
    public List<TaskDTO> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO);
        return ResponseEntity.ok("Tarefa atualizada com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Tarefa deletada com sucesso!");
    }
}
