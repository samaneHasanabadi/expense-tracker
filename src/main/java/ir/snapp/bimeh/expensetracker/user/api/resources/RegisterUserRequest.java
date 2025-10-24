package ir.snapp.bimeh.expensetracker.user.api.resources;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(@NotBlank String name, @NotBlank String username, @NotBlank String password, String email) {
}
