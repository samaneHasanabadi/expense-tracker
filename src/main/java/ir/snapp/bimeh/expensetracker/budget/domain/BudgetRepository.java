package ir.snapp.bimeh.expensetracker.budget.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetRepository {

    Budget save(Budget budget);

    Optional<Budget> findById(Long budgetId);

    void deleteById(Long budgetId);

    Page<Budget> findAll(Pageable page);

}
