package ir.snapp.bimeh.expensetracker.alert.api;

import ir.snapp.bimeh.expensetracker.alert.application.dto.AlertDTO;
import ir.snapp.bimeh.expensetracker.alert.application.query.GetAlertQuery;
import ir.snapp.bimeh.expensetracker.alert.application.query.handler.GetAlertQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alert")
public class AlertQueryController {

    private final GetAlertQueryHandler getAlertQueryHandler;

    @GetMapping()
    public ResponseEntity<List<AlertDTO>> getAlerts(@RequestParam(required = false) Long categoryId,
                                                    @RequestParam(required = false) String categoryType,
                                                    @RequestParam(required = false) Boolean active,
                                                    @RequestParam(required = false) Boolean triggered,
                                                    @RequestParam(required = false) String status,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) throws AccessDeniedException {

        GetAlertQuery query = new GetAlertQuery(categoryId, categoryType, active, triggered, status, page, size);
        List<AlertDTO> budgets = getAlertQueryHandler.handle(query);

        return ResponseEntity.ok(budgets);
    }
}
