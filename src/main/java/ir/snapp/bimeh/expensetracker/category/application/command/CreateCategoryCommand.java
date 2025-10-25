package ir.snapp.bimeh.expensetracker.category.application.command;

public record CreateCategoryCommand(
        String name,
        Long templateId,
        Long parentId,
        String description
) {
}