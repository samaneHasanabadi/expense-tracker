package ir.snapp.bimeh.expensetracker.user.api;

import ir.snapp.bimeh.expensetracker.user.api.resources.LoginUserRequest;
import ir.snapp.bimeh.expensetracker.user.api.resources.RegisterUserRequest;
import ir.snapp.bimeh.expensetracker.user.application.command.RegisterUserCommand;
import ir.snapp.bimeh.expensetracker.user.application.command.handler.RegisterUserHandler;
import ir.snapp.bimeh.expensetracker.user.application.query.LoginUserCommand;
import ir.snapp.bimeh.expensetracker.user.application.query.handler.LoginUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserQueryController {

    private final LoginUserHandler loginUserHandler;
    private final ConversionService conversionService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequest request) {
        LoginUserCommand convert = conversionService.convert(request, LoginUserCommand.class);
        loginUserHandler.handle(convert);
        return ResponseEntity.ok().body("Login in successful");
    }
}
