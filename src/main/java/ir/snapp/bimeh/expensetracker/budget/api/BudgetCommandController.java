package ir.snapp.bimeh.expensetracker.budget.api;

import ir.snapp.bimeh.expensetracker.budget.api.resources.CreateBudgetRequest;
import ir.snapp.bimeh.expensetracker.budget.api.resources.UpdateBudgetRequest;
import ir.snapp.bimeh.expensetracker.budget.application.command.CreateBudgetCommand;
import ir.snapp.bimeh.expensetracker.budget.application.command.UpdateBudgetCommand;
import ir.snapp.bimeh.expensetracker.budget.application.command.handler.ActiveInActiveBudgetCommandHandler;
import ir.snapp.bimeh.expensetracker.budget.application.command.handler.CreateBudgetCommandHandler;
import ir.snapp.bimeh.expensetracker.budget.application.command.handler.DeleteBudgetCommandHandler;
import ir.snapp.bimeh.expensetracker.budget.application.command.handler.UpdateBudgetCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budget")
public class BudgetCommandController {

    private final ConversionService conversionService;
    private final CreateBudgetCommandHandler createBudgetCommandHandler;
    private final UpdateBudgetCommandHandler updateBudgetCommandHandler;
    private final DeleteBudgetCommandHandler deleteBudgetCommandHandler;
    private final ActiveInActiveBudgetCommandHandler activeInActiveBudgetCommandHandler;

    @PostMapping("/create")
    public ResponseEntity<String> createBudget(@Valid @RequestBody CreateBudgetRequest request) throws AccessDeniedException {
        CreateBudgetCommand command = conversionService.convert(request, CreateBudgetCommand.class);
        createBudgetCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Budget is created successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBudget(@PathVariable Long id, @Valid @RequestBody UpdateBudgetRequest request) throws AccessDeniedException {
        UpdateBudgetCommand command = conversionService.convert(request, UpdateBudgetCommand.class);
        updateBudgetCommandHandler.handle(id, command);
        return ResponseEntity.ok().body("Budget is updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) throws AccessDeniedException {
        deleteBudgetCommandHandler.handle(id);
        return ResponseEntity.ok().body("Budget is deleted successfully!");
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<String> activateBudget(@PathVariable Long id) throws AccessDeniedException {
        activeInActiveBudgetCommandHandler.activate(id);
        return ResponseEntity.ok().body("Budget is activated successfully!");
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateBudget(@PathVariable Long id) throws AccessDeniedException {
        activeInActiveBudgetCommandHandler.deactivate(id);
        return ResponseEntity.ok().body("Budget is deactivated successfully!");
    }
}
