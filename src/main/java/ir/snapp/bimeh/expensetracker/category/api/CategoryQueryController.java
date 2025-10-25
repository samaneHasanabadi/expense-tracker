package ir.snapp.bimeh.expensetracker.category.api;

import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryDTO;
import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryTemplateDTO;
import ir.snapp.bimeh.expensetracker.category.application.query.GetCategoryQuery;
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

    @GetMapping("/template")
    public ResponseEntity<List<CategoryTemplateDTO>> getCategoryTemplates(@RequestParam(required = false) String name,
                                                                          @RequestParam(required = false) String type) {
        GetCategoryTemplateQuery query = new GetCategoryTemplateQuery(name, type);
        List<CategoryTemplateDTO> expenses = getCategoryQueryHandler.getCategoryTemplates(query);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getCategories(@RequestParam(required = false) Long id,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String type,
                                                           @RequestParam(required = false) Long parentId) throws AccessDeniedException {
        GetCategoryQuery query = new GetCategoryQuery(id, name, type, parentId);
        List<CategoryDTO> expenses = getCategoryQueryHandler.getCategories(query);
        return ResponseEntity.ok(expenses);
    }
}
