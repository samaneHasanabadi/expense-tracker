package ir.snapp.bimeh.expensetracker.category.application.query;

public record GetCategoryQuery(
        Long id,
        String name,
        String type,
        Long parentId
) {
}
