package ir.snapp.bimeh.expensetracker.user.api.resources;

import ir.snapp.bimeh.expensetracker.user.application.command.RegisterUserCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserRequestConverter implements Converter<RegisterUserRequest, RegisterUserCommand> {
    @Override
    public RegisterUserCommand convert(RegisterUserRequest source) {
        return new RegisterUserCommand(source.name(), source.username(), source.password(), source.email());
    }
}
