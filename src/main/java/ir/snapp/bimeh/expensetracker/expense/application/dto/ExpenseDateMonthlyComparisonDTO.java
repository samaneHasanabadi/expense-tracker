package ir.snapp.bimeh.expensetracker.expense.application.dto;

import java.util.Date;

public record ExpenseDateMonthlyComparisonDTO(
        Date date,
        Double totalAmountCurrentMonth,
        Double totalAmountPreviousMonth,
        Double totalDifferenceAmount
) {

    public ExpenseDateMonthlyComparisonDTO(Date date, Double totalAmountCurrentMonth, Double totalAmountPreviousMonth) {
        this(date, totalAmountCurrentMonth, totalAmountPreviousMonth, totalAmountCurrentMonth - totalAmountPreviousMonth);
    }
}
