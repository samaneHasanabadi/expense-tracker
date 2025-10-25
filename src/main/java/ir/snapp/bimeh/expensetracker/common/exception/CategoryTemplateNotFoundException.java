package ir.snapp.bimeh.expensetracker.common.exception;

public class CategoryTemplateNotFoundException extends RuntimeException {
    public CategoryTemplateNotFoundException(Long categoryTemplateId) {
        super("Category Template not found with id: " + categoryTemplateId);
    }
}
