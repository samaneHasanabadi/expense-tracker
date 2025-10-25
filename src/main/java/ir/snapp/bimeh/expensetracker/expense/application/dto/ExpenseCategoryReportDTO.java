package ir.snapp.bimeh.expensetracker.expense.application.dto;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;

public record ExpenseCategoryReportDTO(
        Long categoryId,
        String categoryName,

        CategoryType categoryType,
        Double totalAmount
) {
    public ExpenseCategoryReportDTO(Long categoryId, String categoryName, CategoryType categoryType, Double totalAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.totalAmount = totalAmount;
    }
}
