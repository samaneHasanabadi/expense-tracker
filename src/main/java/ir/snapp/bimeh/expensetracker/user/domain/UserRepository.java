package ir.snapp.bimeh.expensetracker.user.domain;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);
}
