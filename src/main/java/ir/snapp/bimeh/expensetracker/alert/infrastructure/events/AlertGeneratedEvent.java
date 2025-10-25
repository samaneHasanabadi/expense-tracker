package ir.snapp.bimeh.expensetracker.alert.infrastructure.events;

import java.util.Date;

public record AlertGeneratedEvent(
        Long alertId,
        Long userId,
        String message,
        Date createdAt
) {
}
