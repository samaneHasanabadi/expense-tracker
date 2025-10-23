package ir.snapp.bimeh.expensetracker.user.infrastructure;

import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {

}
