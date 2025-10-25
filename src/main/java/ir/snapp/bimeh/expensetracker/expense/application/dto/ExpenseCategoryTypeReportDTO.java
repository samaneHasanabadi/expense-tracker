package ir.snapp.bimeh.expensetracker.expense.application.dto;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;

public record ExpenseCategoryTypeReportDTO(
        CategoryType categoryType,
        Double totalAmount
) {
    public ExpenseCategoryTypeReportDTO(CategoryType categoryType, Double totalAmount) {
        this.categoryType = categoryType;
        this.totalAmount = totalAmount;
    }
}
