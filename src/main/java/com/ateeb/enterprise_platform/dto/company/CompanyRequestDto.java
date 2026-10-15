package com.ateeb.enterprise_platform.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CompanyRequestDto(
        @NotBlank(message = "Company name is required")
        @Size(max = 100,message = "Company name must not exceed 100 characters")
        String name
) {
}
