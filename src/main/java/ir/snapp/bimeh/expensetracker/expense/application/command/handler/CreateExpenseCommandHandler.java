package ir.snapp.bimeh.expensetracker.expense.application.command.handler;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.expense.application.command.CreateExpenseCommand;
import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import ir.snapp.bimeh.expensetracker.expense.domain.PaymentMethod;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ir.snapp.bimeh.expensetracker.common.exception.CategoryNotFoundException;
import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CreateExpenseCommandHandler {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(CreateExpenseCommand command) throws AccessDeniedException {
        Expense expense = new Expense();
        expense.setTitle(command.title());
        expense.setAmount(command.amount());
        expense.setIssuedDate(command.issuedDate());
        expense.setOwner(authenticatedUserProvider.getCurrentUser());
        if (command.paymentMethod() != null)
            expense.setPaymentMethod(PaymentMethod.valueOf(command.paymentMethod()));
        expense.setDescription(command.description());
        if (command.categoryId() != null)
            expense.setCategory(categoryRepository.findById(command.categoryId()).orElseThrow(() -> new CategoryNotFoundException(command.categoryId())));

        expenseRepository.save(expense);
    }

}
