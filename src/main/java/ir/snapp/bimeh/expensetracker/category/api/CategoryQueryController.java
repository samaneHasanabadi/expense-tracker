package ir.snapp.bimeh.expensetracker.category.api;

import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryTemplateDTO;
import ir.snapp.bimeh.expensetracker.category.application.query.GetCategoryTemplateQuery;
import ir.snapp.bimeh.expensetracker.category.application.query.handler.GetCategoryQueryHandler;
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
@RequestMapping("/api/category")
public class CategoryQueryController {

    private final GetCategoryQueryHandler getCategoryQueryHandler;

    @GetMapping()
    public ResponseEntity<List<CategoryTemplateDTO>> getcategoryTemplates(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String type) throws AccessDeniedException {

        GetCategoryTemplateQuery query = new GetCategoryTemplateQuery(name, type);
        List<CategoryTemplateDTO> expenses = getCategoryQueryHandler.getCategoryTemplates(query);

        return ResponseEntity.ok(expenses);
    }
}
