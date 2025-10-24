package ir.snapp.bimeh.expensetracker.expense.application.command.handler;

import ir.snapp.bimeh.expensetracker.common.exception.ExpenseNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedExpenseAccessException;
import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class DeleteExpenseCommandHandler {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(Long expenseId) throws AccessDeniedException {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        User currentUser = authenticatedUserProvider.getCurrentUser();
        if (!expense.getOwner().getId().equals(currentUser.getId())) {
            throw new UnauthorizedExpenseAccessException(expenseId);
        }

        expenseRepository.deleteById(expenseId);
    }
}