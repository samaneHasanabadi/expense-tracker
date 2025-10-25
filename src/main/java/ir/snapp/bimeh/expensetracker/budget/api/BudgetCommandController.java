package ir.snapp.bimeh.expensetracker.budget.api;

import ir.snapp.bimeh.expensetracker.budget.api.resources.CreateBudgetRequest;
import ir.snapp.bimeh.expensetracker.budget.application.command.CreateBudgetCommand;
import ir.snapp.bimeh.expensetracker.budget.application.command.handler.CreateBudgetCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budget")
public class BudgetCommandController {

    private final ConversionService conversionService;
    private final CreateBudgetCommandHandler createBudgetCommandHandler;

    @PostMapping("/create")
    public ResponseEntity<String> createBudget(@Valid @RequestBody CreateBudgetRequest request) throws AccessDeniedException {
        CreateBudgetCommand command = conversionService.convert(request, CreateBudgetCommand.class);
        createBudgetCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Budget is created successfully!");
    }
}
