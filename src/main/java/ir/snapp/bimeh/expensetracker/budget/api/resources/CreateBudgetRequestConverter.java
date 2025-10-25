package ir.snapp.bimeh.expensetracker.budget.api.resources;

import ir.snapp.bimeh.expensetracker.budget.application.command.CreateBudgetCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateBudgetRequestConverter implements Converter<CreateBudgetRequest, CreateBudgetCommand> {
    @Override
    public CreateBudgetCommand convert(CreateBudgetRequest source) {
        return new CreateBudgetCommand(source.categoryId(), source.monthlyLimit(), source.alertThresholdPercentage());
    }
}
