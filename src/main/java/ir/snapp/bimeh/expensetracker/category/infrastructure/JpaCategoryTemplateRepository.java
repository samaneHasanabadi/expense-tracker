package ir.snapp.bimeh.expensetracker.category.infrastructure;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplate;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryTemplateRepository extends CategoryTemplateRepository, JpaRepository<CategoryTemplate, Long> {
}
