package ir.snapp.bimeh.expensetracker.expense.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Data
public class UpdateExpenseCommand {

    @NotNull
    private Long expenseId;

    @NotBlank
    private String title;
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private Date issuedDate;

    private Long categoryId;
    @Pattern(regexp = "CARD|CACHE|TRANSFER|OTHER", message = "payment method must be one of : CARD, CACHE, TRANSFER, OTHER")
    private String paymentMethod;
    private String description;
}
