package ir.snapp.bimeh.expensetracker.category.application.command;

public record UpdateCategoryCommand(
        String name,
        Long templateId,
        Long parentId,
        String description
) {
}