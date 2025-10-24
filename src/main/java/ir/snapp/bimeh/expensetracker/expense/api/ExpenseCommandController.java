package ir.snapp.bimeh.expensetracker.expense.api;

import ir.snapp.bimeh.expensetracker.expense.api.resources.CreateExpenseRequest;
import ir.snapp.bimeh.expensetracker.expense.application.command.CreateExpenseCommand;
import ir.snapp.bimeh.expensetracker.expense.application.command.handler.CreateExpenseCommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseCommandController {

    private final ConversionService conversionService;
    private final CreateExpenseCommandHandler createExpenseCommandHandler;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody CreateExpenseRequest request, Principal principal, HttpServletRequest req) throws AccessDeniedException {
        CreateExpenseCommand command = conversionService.convert(request, CreateExpenseCommand.class);
        createExpenseCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense is added successfully!");
    }
}
