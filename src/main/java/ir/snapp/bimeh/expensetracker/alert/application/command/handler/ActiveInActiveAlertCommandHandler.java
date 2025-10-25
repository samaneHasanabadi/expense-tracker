package ir.snapp.bimeh.expensetracker.alert.application.command.handler;

import ir.snapp.bimeh.expensetracker.alert.domain.Alert;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.common.exception.EntityNotFoundException;
import ir.snapp.bimeh.expensetracker.common.exception.UnauthorizedCategoryAccessException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class ActiveInActiveAlertCommandHandler {

    private final AlertRepository alertRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void activate(Long id) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Alert.class.getSimpleName(), id));

        if (!alert.getUser().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        alert.setActive(true);
        alertRepository.save(alert);
    }

    @Transactional
    public void deactivate(Long id) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Alert.class.getSimpleName(), id));

        if (!alert.getUser().getId().equals(currentUser.getId()))
            throw new UnauthorizedCategoryAccessException(id);

        alert.setActive(false);
        alertRepository.save(alert);
    }
}
