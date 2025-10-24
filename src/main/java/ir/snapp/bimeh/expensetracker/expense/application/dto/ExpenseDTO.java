package ir.snapp.bimeh.expensetracker.expense.application.dto;

import java.util.Date;

public record ExpenseDTO(
    Long id,
    String title,
    Double amount,
    Date issuedDate,
    String categoryName,
    String categoryTemplateName,
    String paymentMethod,
    String description,
    String ownerName
) {}
