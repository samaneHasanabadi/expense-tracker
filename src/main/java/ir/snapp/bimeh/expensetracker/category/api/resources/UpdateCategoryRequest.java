package ir.snapp.bimeh.expensetracker.category.api.resources;

import org.springframework.validation.annotation.Validated;

@Validated
public record UpdateCategoryRequest(
        String name,
        Long templateId,
        Long parentId,
        String description
) {
}