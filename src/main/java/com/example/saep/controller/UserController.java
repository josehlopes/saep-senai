package com.example.saep.controller;

import com.example.saep.dto.UserDTO;
import com.example.saep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginScreen() {
        return "redirect:/index.html";
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return "Usuário cadastrado com sucesso!";
    }
}
