package ir.snapp.bimeh.expensetracker.budget.application.command;

public record CreateBudgetCommand(
        Long categoryId,
        Double monthlyLimit,
        Integer alertThresholdPercentage
) {
}
