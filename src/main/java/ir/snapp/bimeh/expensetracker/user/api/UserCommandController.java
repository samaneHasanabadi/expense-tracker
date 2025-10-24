package ir.snapp.bimeh.expensetracker.user.api;

import ir.snapp.bimeh.expensetracker.user.api.resources.RegisterUserRequest;
import ir.snapp.bimeh.expensetracker.user.application.command.RegisterUserCommand;
import ir.snapp.bimeh.expensetracker.user.application.command.handler.RegisterUserHandler;
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
public class UserCommandController {

    private final RegisterUserHandler registerUserHandler;
    private final ConversionService conversionService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserCommand convert = conversionService.convert(request, RegisterUserCommand.class);
        registerUserHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("User with username " + request.username() + " is successfully created");
    }
}
