package ir.snapp.bimeh.expensetracker.category.api.resources;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record CreateCategoryRequest(
        @NotBlank
        String name,
        Long templateId,
        Long parentId,
        String description
) {
}