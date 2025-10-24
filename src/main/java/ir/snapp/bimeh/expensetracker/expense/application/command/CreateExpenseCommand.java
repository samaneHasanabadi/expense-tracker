package ir.snapp.bimeh.expensetracker.expense.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public record CreateExpenseCommand (

    @NotBlank String title,
    @NotNull
    @Positive
    Double amount,
    @NotNull
    Date issuedDate,

    Long categoryId,
    @Pattern(regexp = "CARD|CACHE|TRANSFER|OTHER", message = "payment method must be one of : CARD, CACHE, TRANSFER, OTHER")
    String paymentMethod,
    String description
){}
