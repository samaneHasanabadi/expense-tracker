package ir.snapp.bimeh.expensetracker.alert.application.query;

public record GetAlertQuery(
    Long categoryId,
    String categoryType,
    Boolean active,
    Boolean triggered,
    int page,
    int size
) {
    public GetAlertQuery {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (size > 100) size = 100;
    }
}
