package ir.snapp.bimeh.expensetracker.user.application.query.handler;

import ir.snapp.bimeh.expensetracker.user.application.query.LoginUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserHandler {

    private final AuthenticationManager authenticationManager;

    public void handle(LoginUserQuery command) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.username(), command.password()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

    }
}
