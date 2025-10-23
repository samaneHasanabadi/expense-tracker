package ir.snapp.bimeh.expensetracker.expense.infrastructure;

import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseRepository extends ExpenseRepository, JpaRepository<Expense, Long> {
}
