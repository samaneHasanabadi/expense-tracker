package ir.snapp.bimeh.expensetracker.category.application.command.handler;

import ir.snapp.bimeh.expensetracker.category.application.command.CreateCategoryCommand;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;
import ir.snapp.bimeh.expensetracker.common.exception.CategoryNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.CategoryTemplateNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedCategoryAccessException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CreateCategoryCommandHandler {

    private final CategoryRepository categoryRepository;
    private final CategoryTemplateRepository categoryTemplateRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(CreateCategoryCommand command) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Category category = new Category();
        category.setOwner(currentUser);
        category.setName(command.name());
        category.setDescription(command.description());

        if (command.parentId() != null) {
            Category parent = categoryRepository.findById(command.parentId()).orElseThrow(() -> new CategoryNotFoundException(command.parentId()));
            if (parent.getOwner().getId().equals(currentUser.getId()))
                throw new UnauthorizedCategoryAccessException(command.parentId());
        }

        if (command.templateId() != null)
            category.setTemplate(categoryTemplateRepository.findById(command.templateId()).orElseThrow(() -> new CategoryTemplateNotFoundException(command.templateId())));

        if (category.getTemplate() == null)
            category.setTemplate(categoryTemplateRepository.findByType(CategoryType.OTHER).orElse(null));

        category.assignTemplateFromParent();
        categoryRepository.save(category);
    }
}
