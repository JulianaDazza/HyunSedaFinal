package co.com.hyunseda.user.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String email,@NotBlank String userName, @NotBlank String password,
                             @Valid AuthCreateRoleRequest roleRequest) {
}
