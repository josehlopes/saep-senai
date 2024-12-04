package com.example.saep.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record TaskDTO(
        Integer id,
        Integer userId,
        Optional<String> userName,
        String description,
        String sector,
        Integer priority,
        LocalDateTime registerIn,
        Integer status
) {
}
