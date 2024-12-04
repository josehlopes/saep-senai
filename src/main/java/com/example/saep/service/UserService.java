package com.example.saep.service;

import com.example.saep.database.UserEntity;
import com.example.saep.dto.UserDTO;
import com.example.saep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserDTO user) {
        UserEntity userEntity = new UserEntity(user.name(), user.email());
        userRepository.save(userEntity);
    }

    public List<UserDTO> getAll() {
        return userRepository.getAll().stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
