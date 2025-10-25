package ir.snapp.bimeh.expensetracker.alert.api;

import ir.snapp.bimeh.expensetracker.alert.application.command.handler.ActiveInActiveAlertCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alert")
public class AlertCommandController {

    private final ActiveInActiveAlertCommandHandler activeInActiveAlertCommandHandler;

    @PatchMapping("/activate/{id}")
    public ResponseEntity<String> activateAlert(@PathVariable Long id) throws AccessDeniedException {
        activeInActiveAlertCommandHandler.activate(id);
        return ResponseEntity.ok().body("Alert is activated successfully!");
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateAlert(@PathVariable Long id) throws AccessDeniedException {
        activeInActiveAlertCommandHandler.deactivate(id);
        return ResponseEntity.ok().body("Alert is deactivated successfully!");
    }
}
