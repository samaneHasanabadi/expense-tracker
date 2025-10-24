package ir.snapp.bimeh.expensetracker.expense.domain;

import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Long id);

    void deleteById(Long id);
}
