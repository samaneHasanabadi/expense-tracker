package ir.snapp.bimeh.expensetracker.budget.application.command.handler;

import ir.snapp.bimeh.expensetracker.budget.domain.Budget;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
import ir.snapp.bimeh.expensetracker.common.exception.EntityNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedCategoryAccessException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class ActiveInActiveBudgetCommandHandler {

    private final BudgetRepository budgetRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void activate(Long id) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Budget.class.getSimpleName(), id));

        if (!budget.getUser().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        budget.setActive(true);
        budgetRepository.save(budget);
    }

    @Transactional
    public void deactivate(Long id) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Budget.class.getSimpleName(), id));

        if (!budget.getUser().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        budget.setActive(false);
        budgetRepository.save(budget);
    }
}
