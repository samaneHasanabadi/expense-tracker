package ir.snapp.bimeh.expensetracker.budget.domain;

import java.util.Optional;

public interface BudgetRepository {

    Budget save(Budget budget);
    Optional<Budget> findById(Long budgetId);
    void deleteById(Long budgetId);

}
