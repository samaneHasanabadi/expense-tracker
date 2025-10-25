package ir.snapp.bimeh.expensetracker.category.application.command.handler;

import io.netty.util.internal.StringUtil;
import ir.snapp.bimeh.expensetracker.category.application.command.UpdateCategoryCommand;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplate;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import ir.snapp.bimeh.expensetracker.common.exception.EntityNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedCategoryAccessException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class UpdateCategoryCommandHandler {

    private final CategoryRepository categoryRepository;
    private final CategoryTemplateRepository categoryTemplateRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(Long id, UpdateCategoryCommand command) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), id));

        if (!category.getOwner().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        if (!StringUtil.isNullOrEmpty(command.name()))
            category.setName(command.name());

        if (command.description() != null)
            category.setDescription(command.description());

        if (command.parentId() != null) {
            Category parent = categoryRepository.findById(command.parentId()).orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), command.parentId()));
            if (!parent.getOwner().getId().equals(currentUser.getId()))
                throw new UnauthorizedCategoryAccessException(command.parentId());

            category.setParent(parent);
        } else
            category.setParent(null);

        if (command.templateId() != null)
            category.setTemplate(categoryTemplateRepository.findById(command.templateId()).orElseThrow(() -> new EntityNotFoundException(CategoryTemplate.class.getSimpleName(), command.templateId())));

        category.assignTemplateFromParent();
        categoryRepository.save(category);
    }
}
