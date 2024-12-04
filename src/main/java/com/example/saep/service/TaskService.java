package com.example.saep.service;

import com.example.saep.database.TaskEntity;
import com.example.saep.database.UserEntity;
import com.example.saep.dto.TaskDTO;
import com.example.saep.repository.TaskRepository;
import com.example.saep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public void create(TaskDTO taskDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(taskDTO.userId());

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            TaskEntity taskEntity = new TaskEntity(
                    null,
                    user,
                    taskDTO.description(),
                    taskDTO.sector(),
                    taskDTO.priority(),
                    LocalDateTime.now(),
                    taskDTO.status()
            );
            taskRepository.save(taskEntity);
            System.out.println("Tarefa criada com sucesso para o usuário " + user.getName());
        } else {
            throw new IllegalArgumentException("Usuário com ID " + taskDTO.userId() + " não encontrado.");
        }
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.getAll().stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getUser().getId(),
                        Optional.ofNullable(task.getUser().getName()),
                        task.getDescription(),
                        task.getSector(),
                        task.getPriority(),
                        task.getRegisterIn(),
                        task.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public void updateTask(TaskDTO taskDTO) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(taskDTO.id());
        if (optionalTask.isPresent()) {
            TaskEntity existingTask = optionalTask.get();

            existingTask.setDescription(taskDTO.description() != null ? taskDTO.description() : existingTask.getDescription());
            existingTask.setUser(userRepository.findById(taskDTO.userId()).orElse(existingTask.getUser()));
            existingTask.setSector(taskDTO.sector() != null ? taskDTO.sector() : existingTask.getSector());
            existingTask.setPriority(taskDTO.priority() != null ? taskDTO.priority() : existingTask.getPriority());
            existingTask.setRegisterIn(taskDTO.registerIn() != null ? taskDTO.registerIn() : existingTask.getRegisterIn());
            existingTask.setStatus(taskDTO.status() != null ? taskDTO.status() : existingTask.getStatus());

            taskRepository.update(existingTask);
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }

    public TaskDTO getTaskById(Integer id) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            TaskEntity task = optionalTask.get();
            return new TaskDTO(
                    task.getId(),
                    task.getUser().getId(),
                    Optional.ofNullable(task.getUser().getName()),
                    task.getDescription(),
                    task.getSector(),
                    task.getPriority(),
                    task.getRegisterIn(),
                    task.getStatus()
            );
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }


    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
