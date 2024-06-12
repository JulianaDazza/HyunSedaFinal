package co.com.hyunseda.user.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String userName, @NotBlank String password) {

}
