package ir.snapp.bimeh.expensetracker.expense.application.dto;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;

public record ExpenseCategoryTypeMonthlyComparisonDTO(
        CategoryType categoryType,
        Double totalAmountCurrentMonth,
        Double totalAmountPreviousMonth,
        Double totalDifferenceAmount
) {
    public ExpenseCategoryTypeMonthlyComparisonDTO(CategoryType categoryType, Double totalAmountCurrentMonth, Double totalAmountPreviousMonth) {
        this(categoryType, totalAmountCurrentMonth, totalAmountPreviousMonth, totalAmountCurrentMonth - totalAmountPreviousMonth);
    }
}
