package ir.snapp.bimeh.expensetracker.common.exception;

import ir.snapp.bimeh.expensetracker.budget.domain.Budget;

public class ActiveBudgetIsAlreadyExistsException extends RuntimeException {
    public ActiveBudgetIsAlreadyExistsException(Budget budget) {
        super("Active budget for " + (budget.getCategory() == null ? "month" : (" category ") + budget.getCategory().getName()) + " is already exists!");
    }
}
