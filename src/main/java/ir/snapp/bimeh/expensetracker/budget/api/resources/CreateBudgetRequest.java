package ir.snapp.bimeh.expensetracker.budget.api.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record CreateBudgetRequest(
        Long categoryId,
        @NotNull @Positive
        Double monthlyLimit,
        @NotNull @Min(40) @Max(100)
        Integer alertThresholdPercentage
) {
}
