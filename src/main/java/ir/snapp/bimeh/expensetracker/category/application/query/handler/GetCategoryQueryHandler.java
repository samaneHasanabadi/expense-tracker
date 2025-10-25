package ir.snapp.bimeh.expensetracker.category.application.query.handler;

import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryDTO;
import ir.snapp.bimeh.expensetracker.category.application.dto.CategoryTemplateDTO;
import ir.snapp.bimeh.expensetracker.category.application.query.GetCategoryQuery;
import ir.snapp.bimeh.expensetracker.category.application.query.GetCategoryTemplateQuery;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCategoryQueryHandler {

    private final CategoryTemplateRepository categoryTemplateRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public List<CategoryTemplateDTO> getCategoryTemplates(GetCategoryTemplateQuery query) {
        return categoryTemplateRepository.findAll().stream()
                .filter(c -> query.name() == null || c.getName().equals(query.name()))
                .filter(c -> query.type() == null || c.matchesTypeInHierarchy(query.type()))
                .map(c -> new CategoryTemplateDTO(c.getName(), c.getType().name(), c.getParent() == null ? null : c.getParent().getId(), c.getParent() == null ? null : c.getParent().getName()))
                .collect(Collectors.toList());

    }

    public List<CategoryDTO> getCategories(GetCategoryQuery query) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        return categoryRepository.findAll().stream()
                .filter(c -> query.id() == null || c.getId().equals(query.id()))
                .filter(c -> query.name() == null || c.getName().equals(query.name()))
                .filter(c -> query.type() == null || c.matchesTypeInHierarchy(query.type()))
                .filter(c -> query.parentId() == null || c.matchesCategoryInHierarchy(query.parentId(), false))
                .filter(c -> currentUser.getId().equals(c.getOwner().getId()))
                .map(c -> new CategoryDTO(c.getId(), c.getName(), c.getDescription(), c.getType().name(), c.getParent() == null ? null : c.getParent().getId(), c.getParent() == null ? null : c.getParent().getName(), c.getTemplate() == null ? null : c.getTemplate().getId(), c.getTemplate() == null ? null : c.getTemplate().getName(), c.getOwner().getName()))
                .collect(Collectors.toList());
    }
}
