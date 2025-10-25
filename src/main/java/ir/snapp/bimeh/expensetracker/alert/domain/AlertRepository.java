package ir.snapp.bimeh.expensetracker.alert.domain;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {

    Alert save(Alert alert);

    Page<Alert> findAll(Pageable page);

    Optional<Alert> findById(Long id);

    List<Alert> findTop100ByStatusAndActive(AlertStatus status, Boolean active);

    void deactivateAlerts(AlertStatus status, User user, Category category);
}
