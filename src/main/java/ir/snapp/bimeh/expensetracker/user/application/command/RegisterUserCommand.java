package ir.snapp.bimeh.expensetracker.user.application.command;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserCommand (
    @NotBlank
    String name,
    @NotBlank
    String username,
    @NotBlank
    String password,
    String email
){}
