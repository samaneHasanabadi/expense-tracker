package ir.snapp.bimeh.expensetracker.category.domain;

import java.util.Optional;

public interface CategoryTemplateRepository {

    Optional<CategoryTemplate> findByName(String name);
    CategoryTemplate save(CategoryTemplate categoryTemplate);
    long count();
}
