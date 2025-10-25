package ir.snapp.bimeh.expensetracker.expense.application.dto;

import java.util.List;

public record ExpenseMonthlyComparisonReportDTO(
        String ownerName,
        Integer year,
        Integer month,
        Integer yearOfPreviousMonth,
        Integer previousMonth,
        Double totalAmountCurrentMonth,
        Double totalAmountPreviousMonth,
        Double totalDifferenceAmount,
        List<ExpenseCategoryMonthlyComparisonDTO> byCategory,
        List<ExpenseCategoryTypeMonthlyComparisonDTO> byCategoryType,
        List<ExpenseDateMonthlyComparisonDTO> daily
) {
}
