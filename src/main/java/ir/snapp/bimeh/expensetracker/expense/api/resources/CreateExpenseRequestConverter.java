package ir.snapp.bimeh.expensetracker.expense.api.resources;

import ir.snapp.bimeh.expensetracker.expense.application.command.CreateExpenseCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateExpenseRequestConverter implements Converter<CreateExpenseRequest, CreateExpenseCommand> {
    @Override
    public CreateExpenseCommand convert(CreateExpenseRequest source) {
        return new CreateExpenseCommand(source.title(), source.amount(), source.issuedDate(), source.categoryId(), source.paymentMethod(), source.description());
    }
}
