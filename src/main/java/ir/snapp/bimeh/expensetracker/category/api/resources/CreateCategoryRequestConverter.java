package ir.snapp.bimeh.expensetracker.category.api.resources;

import ir.snapp.bimeh.expensetracker.category.application.command.CreateCategoryCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryRequestConverter implements Converter<CreateCategoryRequest, CreateCategoryCommand> {
    @Override
    public CreateCategoryCommand convert(CreateCategoryRequest source) {
        return new CreateCategoryCommand(source.name(), source.templateId(), source.parentId(), source.description());
    }
}
