package ir.snapp.bimeh.expensetracker.expense.application.query;

import java.util.Date;

public record GetExpensesQuery(
    Date fromDate,
    Date toDate,
    Long categoryId,
    String categoryType,
    String paymentMethod,
    int page,
    int size
) {
    public GetExpensesQuery {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (size > 100) size = 100;
    }
}
