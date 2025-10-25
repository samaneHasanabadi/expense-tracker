package ir.snapp.bimeh.expensetracker.budget.application.command.handler;

import ir.snapp.bimeh.expensetracker.budget.application.command.UpdateBudgetCommand;
import ir.snapp.bimeh.expensetracker.budget.domain.Budget;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.common.exception.EntityNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedCategoryAccessException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class UpdateBudgetCommandHandler {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public void handle(Long id, UpdateBudgetCommand command) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Budget.class.getSimpleName(), id));

        if (!budget.getUser().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        if (command.categoryId() != null) {
            Category category = categoryRepository.findById(command.categoryId()).orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), command.categoryId()));
            budget.setCategory(category);
        } else
            budget.setCategory(null);

        budget.setUser(currentUser);
        budget.setMonthlyLimit(command.monthlyLimit());
        budget.setAlertThresholdPercentage(command.alertThresholdPercentage());
        budgetRepository.save(budget);
    }
}
