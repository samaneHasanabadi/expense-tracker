package ir.snapp.bimeh.expensetracker.category.api.resources;

import ir.snapp.bimeh.expensetracker.category.application.command.UpdateCategoryCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryRequestConverter implements Converter<UpdateCategoryRequest, UpdateCategoryCommand> {
    @Override
    public UpdateCategoryCommand convert(UpdateCategoryRequest source) {
        return new UpdateCategoryCommand(source.name(), source.templateId(), source.parentId(), source.description());
    }
}
