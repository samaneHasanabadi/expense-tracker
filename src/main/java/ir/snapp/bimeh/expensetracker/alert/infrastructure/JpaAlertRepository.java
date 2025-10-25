package ir.snapp.bimeh.expensetracker.alert.infrastructure;

import ir.snapp.bimeh.expensetracker.alert.domain.Alert;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlertRepository extends AlertRepository, JpaRepository<Alert, Long> {
}
