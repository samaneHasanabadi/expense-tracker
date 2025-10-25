package ir.snapp.bimeh.expensetracker.category.domain;

import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByType(CategoryType type);

    void deleteById(Long id);
}
