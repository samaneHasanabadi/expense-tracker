package ir.snapp.bimeh.expensetracker.alert.infrastructure.events;

import java.util.Date;

public record AlertNotifiedEvent(
        Long alertId,
        Date notifiedAt
) {
}
