package ir.snapp.bimeh.expensetracker.category.api;

import ir.snapp.bimeh.expensetracker.category.api.resources.CreateCategoryRequest;
import ir.snapp.bimeh.expensetracker.category.api.resources.UpdateCategoryRequest;
import ir.snapp.bimeh.expensetracker.category.application.command.CreateCategoryCommand;
import ir.snapp.bimeh.expensetracker.category.application.command.UpdateCategoryCommand;
import ir.snapp.bimeh.expensetracker.category.application.command.handler.CreateCategoryCommandHandler;
import ir.snapp.bimeh.expensetracker.category.application.command.handler.UpdateCategoryCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryCommandController {

    private final ConversionService conversionService;
    private final CreateCategoryCommandHandler createCategoryCommandHandler;
    private final UpdateCategoryCommandHandler updateCategoryCommandHandler;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryRequest request) throws AccessDeniedException {
        CreateCategoryCommand command = conversionService.convert(request, CreateCategoryCommand.class);
        createCategoryCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category is created successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest request) throws AccessDeniedException {
        UpdateCategoryCommand command = conversionService.convert(request, UpdateCategoryCommand.class);
        updateCategoryCommandHandler.handle(id, command);
        return ResponseEntity.status(HttpStatus.OK).body("Category is updated successfully!");
    }
}
