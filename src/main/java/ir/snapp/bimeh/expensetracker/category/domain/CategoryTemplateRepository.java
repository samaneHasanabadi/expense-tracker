package ir.snapp.bimeh.expensetracker.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryTemplateRepository {

    Optional<CategoryTemplate> findByName(String name);
    Optional<CategoryTemplate> findById(Long id);
    Optional<CategoryTemplate> findByType(CategoryType type);
    CategoryTemplate save(CategoryTemplate categoryTemplate);
    long count();

    List<CategoryTemplate> findAll();
}
