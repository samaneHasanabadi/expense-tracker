package ir.snapp.bimeh.expensetracker.category.infrastructure;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends CategoryRepository, JpaRepository<Category, Long> {
}
