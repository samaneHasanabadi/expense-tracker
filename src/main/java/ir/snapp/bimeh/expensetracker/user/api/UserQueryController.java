package ir.snapp.bimeh.expensetracker.user.api;

import ir.snapp.bimeh.expensetracker.user.api.resources.LoginUserRequest;
import ir.snapp.bimeh.expensetracker.user.application.query.LoginUserQuery;
import ir.snapp.bimeh.expensetracker.user.application.query.handler.LoginUserHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequest request, HttpServletRequest req) {
        LoginUserQuery convert = conversionService.convert(request, LoginUserQuery.class);
        loginUserHandler.handle(convert);
        
        var session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        
        return ResponseEntity.ok().body("Login in successful");
    }
}
