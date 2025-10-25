package ir.snapp.bimeh.expensetracker.alert.infrastructure;

import ir.snapp.bimeh.expensetracker.alert.domain.Alert;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertStatus;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlertRepository extends AlertRepository, JpaRepository<Alert, Long> {

    @Modifying
    @Transactional
    @Query("update Alert a set a.active = false where a.status = :status and a.user = :user and (:category is null and a.category is null) or (a.category = :category)")
    void deactivateAlerts(AlertStatus status, User user, Category category);

}
