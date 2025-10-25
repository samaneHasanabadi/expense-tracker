package ir.snapp.bimeh.expensetracker.budget.application.command.handler;

import ir.snapp.bimeh.expensetracker.budget.application.command.CreateBudgetCommand;
import ir.snapp.bimeh.expensetracker.budget.domain.Budget;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.common.exception.EntityNotFoundException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CreateBudgetCommandHandler {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(CreateBudgetCommand command) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Budget budget = new Budget();

        if (command.categoryId() != null) {
            Category category = categoryRepository.findById(command.categoryId()).orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), command.categoryId()));
            budget.setCategory(category);
        }
        budget.setUser(currentUser);
        budget.setMonthlyLimit(command.monthlyLimit());
        budget.setAlertThresholdPercentage(command.alertThresholdPercentage());
        budget.setActive(true);
        budgetRepository.save(budget);
    }
}
