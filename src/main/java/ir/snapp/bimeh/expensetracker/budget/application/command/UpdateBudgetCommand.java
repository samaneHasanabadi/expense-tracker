package ir.snapp.bimeh.expensetracker.budget.application.command;

public record UpdateBudgetCommand(
        Long categoryId,
        Double monthlyLimit,
        Integer alertThresholdPercentage
) {
}
