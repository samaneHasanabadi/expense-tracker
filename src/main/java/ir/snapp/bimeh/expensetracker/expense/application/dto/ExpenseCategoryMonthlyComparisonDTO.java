package ir.snapp.bimeh.expensetracker.expense.application.dto;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;

public record ExpenseCategoryMonthlyComparisonDTO(
        Long categoryId,
        String categoryName,
        CategoryType categoryType,
        Double totalAmountCurrentMonth,
        Double totalAmountPreviousMonth,
        Double totalDifferenceAmount
) {
    public ExpenseCategoryMonthlyComparisonDTO(Long categoryId, String categoryName, CategoryType categoryType, Double totalAmountCurrentMonth, Double totalAmountPreviousMonth) {
        this(categoryId, categoryName, categoryType, totalAmountCurrentMonth, totalAmountPreviousMonth, totalAmountCurrentMonth - totalAmountPreviousMonth);
    }
}
