package ir.snapp.bimeh.expensetracker.alert.application.dto;

import java.util.Date;

public record AlertDTO(

        Long id,
        String ownerName,
        Long categoryId,
        String categoryName,
        Double thresholdAmount,
        String message,
        Boolean active,
        Date lastTriggeredAt
) {
}
