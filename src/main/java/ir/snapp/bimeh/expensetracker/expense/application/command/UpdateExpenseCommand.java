package ir.snapp.bimeh.expensetracker.expense.application.command;

import java.util.Date;

public record UpdateExpenseCommand(
    String title,
    Double amount,
    Date issuedDate,
    Long categoryId,
    String paymentMethod,
    String description
) {}