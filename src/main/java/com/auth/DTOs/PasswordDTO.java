package com.auth.DTOs;

import jakarta.validation.constraints.NotBlank;

public record PasswordDTO(
        @NotBlank
        String newPassword
) {
}
