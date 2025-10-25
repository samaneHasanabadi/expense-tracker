package ir.snapp.bimeh.expensetracker.budget.application.query;

import java.util.Date;

public record GetBudgetsQuery(
    Long categoryId,
    String categoryType,
    Boolean active,
    int page,
    int size
) {
    public GetBudgetsQuery {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (size > 100) size = 100;
    }
}
