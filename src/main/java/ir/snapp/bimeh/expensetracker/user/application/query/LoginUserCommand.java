package ir.snapp.bimeh.expensetracker.user.application.query;

import jakarta.validation.constraints.NotBlank;

public record LoginUserCommand(@NotBlank String username, @NotBlank String password) { }
