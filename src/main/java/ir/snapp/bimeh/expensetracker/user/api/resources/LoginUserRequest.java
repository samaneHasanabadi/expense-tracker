package ir.snapp.bimeh.expensetracker.user.api.resources;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(@NotBlank String username, @NotBlank String password) {
}
