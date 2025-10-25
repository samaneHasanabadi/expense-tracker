package ir.snapp.bimeh.expensetracker.expense.application.dto;

import java.util.Date;

public record ExpenseDateReportDTO(
        Date date,
        Double totalAmount
) {
    public ExpenseDateReportDTO(Date date, Double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }
}
