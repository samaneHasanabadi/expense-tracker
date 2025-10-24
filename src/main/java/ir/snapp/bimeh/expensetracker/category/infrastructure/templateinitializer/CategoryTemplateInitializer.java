package ir.snapp.bimeh.expensetracker.category.infrastructure.templateinitializer;

import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplate;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryTemplateRepository;
import ir.snapp.bimeh.expensetracker.category.domain.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryTemplateInitializer implements CommandLineRunner {

    private final CategoryTemplateRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            List.of(
                    new CategoryTemplate("Food", CategoryType.FOOD, null),
                    new CategoryTemplate("Transportation", CategoryType.TRANSPORTATION, null),
                    new CategoryTemplate("Health", CategoryType.HEALTH, null),
                    new CategoryTemplate("Utilities", CategoryType.UTILITIES, null),
                    new CategoryTemplate("Entertainment", CategoryType.ENTERTAINMENT, null),
                    new CategoryTemplate("Rent", CategoryType.RENT, null),
                    new CategoryTemplate("Other", CategoryType.OTHER, null)
            ).forEach(c -> {
                c.assignTypeFromParent();
                repository.save(c);
            });
            List.of(
                    new CategoryTemplate("Coffee", CategoryType.COFFEE, repository.findByName("Food").orElse(null)),
                    new CategoryTemplate("Travel", CategoryType.TRAVEL, repository.findByName("Entertainment").orElse(null))
            ).forEach(c -> {
                c.assignTypeFromParent();
                repository.save(c);
            });
        }
    }
}
