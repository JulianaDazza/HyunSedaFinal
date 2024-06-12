package co.com.hyunseda.user.controller.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public record AuthCreateRoleRequest(@Size(max = 2, message = "El usuario no puede tener más de 2 roles")
                                    List<String> roleListName) {
}
