package ir.snapp.bimeh.expensetracker.alert.application.scheduler;

import ir.snapp.bimeh.expensetracker.alert.domain.Alert;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertStatus;
import ir.snapp.bimeh.expensetracker.alert.infrastructure.events.AlertGeneratedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlertEventPublisher {

    private final AlertRepository alertRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(cron = "${app.alert.scheduler-publishing}")
    @Transactional
    public void publishNewAlerts() {
        List<Alert> alertList = alertRepository.findTop100ByStatusAndActive(AlertStatus.CREATED, true);
        if (alertList.isEmpty())
            return;

        for (Alert alert : alertList) {
            try {
                AlertGeneratedEvent event = new AlertGeneratedEvent(alert.getId(), alert.getUser().getId(), alert.getMessage(), alert.getCreatedAt());
                kafkaTemplate.send("alerts.generated", alert.getId().toString(), event);
                alert.setStatus(AlertStatus.PUBLISHED);
            } catch (Exception e) {
                alert.setStatus(AlertStatus.FAILED);
            }
            alertRepository.save(alert);
        }
    }
}
