package ir.snapp.bimeh.expensetracker.common.exception;

public class UnauthorizedCategoryAccessException extends RuntimeException {
    public UnauthorizedCategoryAccessException(Long categoryId) {
        super("You are not authorized to access category with id: " + categoryId);
    }
}
