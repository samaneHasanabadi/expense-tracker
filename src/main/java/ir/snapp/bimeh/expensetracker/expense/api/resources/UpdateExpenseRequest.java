package ir.snapp.bimeh.expensetracker.expense.api.resources;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public record UpdateExpenseRequest(
        String title,
        @Positive
        Double amount,
        Date issuedDate,
        Long categoryId,
        @Pattern(regexp = "CARD|CACHE|TRANSFER|OTHER", message = "payment method must be one of : CARD, CACHE, TRANSFER, OTHER")
        String paymentMethod,
        String description
) {
}