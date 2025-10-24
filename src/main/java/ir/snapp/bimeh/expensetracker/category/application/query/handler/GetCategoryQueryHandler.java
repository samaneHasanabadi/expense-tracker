package ir.snapp.bimeh.expensetracker.category.application.query.handler;

import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryTemplateDTO;
import ir.snapp.bimeh.expensetracker.category.application.query.GetCategoryTemplateQuery;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCategoryQueryHandler {

    private final CategoryTemplateRepository categoryTemplateRepository;

    public List<CategoryTemplateDTO> getCategoryTemplates(GetCategoryTemplateQuery query) {
        return categoryTemplateRepository.findAll().stream()
                .filter(c -> query.name() == null || c.getName().equals(query.name()))
                .filter(c -> query.type() == null || c.matchesTypeInHierarchy(query.type()))
                .map(c -> new CategoryTemplateDTO(c.getName(), c.getType().name(), c.getParent() == null ? null : c.getParent().getId(), c.getParent() == null ? null : c.getParent().getName()))
                .collect(Collectors.toList());

    }
}
