package ir.snapp.bimeh.expensetracker.expense.api.resources;

import ir.snapp.bimeh.expensetracker.expense.application.command.UpdateExpenseCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateExpenseRequestConverter implements Converter<UpdateExpenseRequest, UpdateExpenseCommand> {
    @Override
    public UpdateExpenseCommand convert(UpdateExpenseRequest source) {
        return new UpdateExpenseCommand(source.title(), source.amount(), source.issuedDate(), source.categoryId(), source.paymentMethod(), source.description());
    }
}
