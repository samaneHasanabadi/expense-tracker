package ir.snapp.bimeh.expensetracker.expense.api;

import ir.snapp.bimeh.expensetracker.expense.api.resources.CreateExpenseRequest;
import ir.snapp.bimeh.expensetracker.expense.api.resources.UpdateExpenseRequest;
import ir.snapp.bimeh.expensetracker.expense.application.command.CreateExpenseCommand;
import ir.snapp.bimeh.expensetracker.expense.application.command.UpdateExpenseCommand;
import ir.snapp.bimeh.expensetracker.expense.application.command.handler.CreateExpenseCommandHandler;
import ir.snapp.bimeh.expensetracker.expense.application.command.handler.DeleteExpenseCommandHandler;
import ir.snapp.bimeh.expensetracker.expense.application.command.handler.UpdateExpenseCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseCommandController {

    private final ConversionService conversionService;
    private final CreateExpenseCommandHandler createExpenseCommandHandler;
    private final UpdateExpenseCommandHandler updateExpenseCommandHandler;
    private final DeleteExpenseCommandHandler deleteExpenseCommandHandler;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@Valid @RequestBody CreateExpenseRequest request) throws AccessDeniedException {
        CreateExpenseCommand command = conversionService.convert(request, CreateExpenseCommand.class);
        createExpenseCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense is added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @Valid @RequestBody UpdateExpenseRequest request) throws AccessDeniedException {
        UpdateExpenseCommand command = conversionService.convert(request, UpdateExpenseCommand.class);
        updateExpenseCommandHandler.handle(id, command);
        return ResponseEntity.status(HttpStatus.OK).body("Expense is updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) throws AccessDeniedException {
        deleteExpenseCommandHandler.handle(id);
        return ResponseEntity.status(HttpStatus.OK).body("Expense is deleted successfully!");
    }
}
