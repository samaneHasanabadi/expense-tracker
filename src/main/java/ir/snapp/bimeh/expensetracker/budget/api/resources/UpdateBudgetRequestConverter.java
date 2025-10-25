package ir.snapp.bimeh.expensetracker.budget.api.resources;

import ir.snapp.bimeh.expensetracker.budget.application.command.UpdateBudgetCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateBudgetRequestConverter implements Converter<UpdateBudgetRequest, UpdateBudgetCommand> {
    @Override
    public UpdateBudgetCommand convert(UpdateBudgetRequest source) {
        return new UpdateBudgetCommand(source.categoryId(), source.monthlyLimit(), source.alertThresholdPercentage());
    }
}
