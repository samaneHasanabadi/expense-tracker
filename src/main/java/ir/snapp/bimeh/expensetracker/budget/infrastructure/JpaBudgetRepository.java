package ir.snapp.bimeh.expensetracker.budget.infrastructure;

import ir.snapp.bimeh.expensetracker.budget.domain.Budget;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBudgetRepository extends BudgetRepository, JpaRepository<Budget, Long> {
}
