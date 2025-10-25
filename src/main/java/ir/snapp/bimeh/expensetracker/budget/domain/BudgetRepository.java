package ir.snapp.bimeh.expensetracker.budget.domain;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository {

    Budget save(Budget budget);

    Optional<Budget> findById(Long budgetId);

    void deleteById(Long budgetId);

    Page<Budget> findAll(Pageable page);

    Optional<Budget> findByCategoryAndActive(Category category, Boolean active);

    List<Budget> findByUserAndActive(User user, Boolean active);

}
