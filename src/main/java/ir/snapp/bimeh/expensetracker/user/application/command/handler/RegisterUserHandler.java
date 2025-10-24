package ir.snapp.bimeh.expensetracker.user.application.command.handler;

import ir.snapp.bimeh.expensetracker.user.application.command.RegisterUserCommand;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void handle(RegisterUserCommand command) {
        if (userRepository.findByUsername(command.username()).isPresent())
            throw new RuntimeException("Username is already used, pick another one");

        User user = new User(command.name(), command.username(), passwordEncoder.encode(command.password()), command.email());
        userRepository.save(user);
    }

}
