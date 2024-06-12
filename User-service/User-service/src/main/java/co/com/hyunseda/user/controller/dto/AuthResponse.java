package co.com.hyunseda.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt","status"})//Dar orden a la respuesta
public record AuthResponse(String userName, String message, String jwt, Boolean status) {

}
