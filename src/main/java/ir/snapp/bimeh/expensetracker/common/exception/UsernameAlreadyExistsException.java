package ir.snapp.bimeh.expensetracker.common.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username '" + username + "' is already taken. Please choose a different username.");
    }
}
