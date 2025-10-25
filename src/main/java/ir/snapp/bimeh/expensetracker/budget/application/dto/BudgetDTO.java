package ir.snapp.bimeh.expensetracker.budget.application.dto;

public record BudgetDTO(

        Long id,
        String ownerName,
        Long categoryId,
        String categoryName,
        Double monthlyLimit,
        Integer alertThresholdPercentage,
        Boolean active
) {
}
