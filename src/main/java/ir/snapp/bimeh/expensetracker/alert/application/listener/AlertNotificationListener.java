package ir.snapp.bimeh.expensetracker.alert.application.listener;

import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertStatus;
import ir.snapp.bimeh.expensetracker.alert.infrastructure.events.AlertNotifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertNotificationListener {

    private final AlertRepository alertRepository;

    @KafkaListener(topics = "alerts.notified", groupId = "alert-service")
    public void onAlertNotified(AlertNotifiedEvent event) {
        alertRepository.findById(event.alertId()).ifPresent(alert -> {
            alert.setStatus(AlertStatus.NOTIFIED);
            alert.setLastTriggeredAt(event.notifiedAt());
            alertRepository.save(alert);
        });
    }
}
