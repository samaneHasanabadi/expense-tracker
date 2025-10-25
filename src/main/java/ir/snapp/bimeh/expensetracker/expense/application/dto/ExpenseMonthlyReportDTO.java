package ir.snapp.bimeh.expensetracker.expense.application.dto;

import java.util.List;

public record ExpenseMonthlyReportDTO(
        String ownerName,
        Double totalAmount,
        Integer year,
        Integer month,
        List<ExpenseCategoryReportDTO> byCategory,
        List<ExpenseCategoryTypeReportDTO> byCategoryType,
        List<ExpenseDateReportDTO> daily
) {
}

