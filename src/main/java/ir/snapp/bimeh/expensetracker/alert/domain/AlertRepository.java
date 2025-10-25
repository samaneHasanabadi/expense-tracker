package ir.snapp.bimeh.expensetracker.alert.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertRepository {

    Alert save(Alert alert);
    Page<Alert> findAll(Pageable page);

}
