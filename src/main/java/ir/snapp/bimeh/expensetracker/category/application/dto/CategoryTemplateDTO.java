package ir.snapp.bimeh.expensetracker.category.application.dto;

public record CategoryTemplateDTO(
        String name,
        String type,
        Long parentId,
        String parentName
) {
}