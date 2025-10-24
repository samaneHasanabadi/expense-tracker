package ir.snapp.bimeh.expensetracker.common.exception;

public class UnauthorizedExpenseAccessException extends RuntimeException {
    public UnauthorizedExpenseAccessException(Long expenseId) {
        super("You are not authorized to access expense with id: " + expenseId);
    }
}
