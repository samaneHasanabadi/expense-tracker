package ir.snapp.bimeh.expensetracker.user.api.resources;

import ir.snapp.bimeh.expensetracker.user.application.query.LoginUserQuery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginUserRequestConverter implements Converter<LoginUserRequest, LoginUserQuery> {
    @Override
    public LoginUserQuery convert(LoginUserRequest source) {
        return new LoginUserQuery(source.username(), source.password());
    }
}
