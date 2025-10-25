package ir.snapp.bimeh.expensetracker.category.application.dto;

public record CategoryDTO(
        Long id,
        String name,
        String description,
        String type,
        Long parentId,
        String parentName,
        Long templateId,
        String templateName,
        String ownerName
) {
}
