package ir.snapp.bimeh.expensetracker.common.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long categoryId) {
        super(entityName + " not found with id: " + categoryId);
    }
}
