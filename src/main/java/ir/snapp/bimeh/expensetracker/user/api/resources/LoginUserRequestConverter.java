package ir.snapp.bimeh.expensetracker.user.api.resources;

import ir.snapp.bimeh.expensetracker.user.application.query.LoginUserCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginUserRequestConverter implements Converter<LoginUserRequest, LoginUserCommand> {
    @Override
    public LoginUserCommand convert(LoginUserRequest source) {
        return new LoginUserCommand(source.username(), source.password());
    }
}
