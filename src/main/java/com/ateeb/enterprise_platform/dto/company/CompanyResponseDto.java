package com.ateeb.enterprise_platform.dto.company;

import java.time.LocalDateTime;

public record CompanyResponseDto(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
