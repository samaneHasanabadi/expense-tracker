package ir.snapp.bimeh.expensetracker.budget.api;

import ir.snapp.bimeh.expensetracker.budget.application.dto.BudgetDTO;
import ir.snapp.bimeh.expensetracker.budget.application.query.GetBudgetsQuery;
import ir.snapp.bimeh.expensetracker.budget.application.query.handler.GetBudgetQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budget")
public class BudgetQueryController {

    private final GetBudgetQueryHandler getBudgetQueryHandler;

    @GetMapping()
    public ResponseEntity<List<BudgetDTO>> getBudgets(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String categoryType,
                                                      @RequestParam(required = false) Boolean active,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) throws AccessDeniedException {

        GetBudgetsQuery query = new GetBudgetsQuery(categoryId, categoryType, active, page, size);
        List<BudgetDTO> budgets = getBudgetQueryHandler.handle(query);

        return ResponseEntity.ok(budgets);
    }
}
