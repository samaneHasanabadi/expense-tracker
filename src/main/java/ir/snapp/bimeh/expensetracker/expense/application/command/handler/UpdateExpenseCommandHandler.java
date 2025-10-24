package ir.snapp.bimeh.expensetracker.expense.application.command.handler;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.common.exception.CategoryNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.ExpenseNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedExpenseAccessException;
import ir.snapp.bimeh.expensetracker.expense.application.command.UpdateExpenseCommand;
import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import ir.snapp.bimeh.expensetracker.expense.domain.PaymentMethod;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class UpdateExpenseCommandHandler {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(Long expenseId, UpdateExpenseCommand command) throws AccessDeniedException {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        User currentUser = authenticatedUserProvider.getCurrentUser();
        if (!expense.getOwner().getId().equals(currentUser.getId())) {
            throw new UnauthorizedExpenseAccessException(expenseId);
        }
        if (command.amount() != null && command.amount() <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than zero");
        }
        if (command.title() != null && command.title().trim().isEmpty()) {
            throw new IllegalArgumentException("Expense title cannot be empty");
        }
        if (command.title() != null) {
            expense.setTitle(command.title());
        }
        if (command.amount() != null) {
            expense.setAmount(command.amount());
        }
        if (command.issuedDate() != null) {
            expense.setIssuedDate(command.issuedDate());
        }
        if (command.paymentMethod() != null) {
            expense.setPaymentMethod(PaymentMethod.valueOf(command.paymentMethod()));
        }
        if (command.description() != null) {
            expense.setDescription(command.description());
        }
        if (command.categoryId() != null) {
            expense.setCategory(categoryRepository.findById(command.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(command.categoryId())));
        }
        expenseRepository.save(expense);
    }
}