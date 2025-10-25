package ir.snapp.bimeh.expensetracker.alert.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlertRepository {

    Alert save(Alert alert);

    Page<Alert> findAll(Pageable page);

    Optional<Alert> findById(Long id);

}
