package ir.snapp.bimeh.expensetracker.alert.application.query.handler;

import ir.snapp.bimeh.expensetracker.alert.application.dto.AlertDTO;
import ir.snapp.bimeh.expensetracker.alert.application.query.GetAlertQuery;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAlertQueryHandler {

    private final AlertRepository alertRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public List<AlertDTO> handle(GetAlertQuery query) throws AccessDeniedException {
        var currentUser = authenticatedUserProvider.getCurrentUser();
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return alertRepository.findAll(pageable).stream()
                .filter(a -> a.getUser().getId().equals(currentUser.getId()))
                .filter(a -> query.categoryId() == null || a.getCategory() != null && a.getCategory().matchesCategoryInHierarchy(query.categoryId(), true))
                .filter(a -> query.categoryType() == null || a.getCategory() != null && a.getCategory().matchesTypeInHierarchy(query.categoryType()))
                .filter(a -> query.active() == null || a.getActive().equals(query.active()))
                .filter(a -> query.triggered() == null || query.triggered() && a.getLastTriggeredAt() != null || !query.triggered() && a.getLastTriggeredAt() == null)
                .filter(a -> query.status() == null || query.status().equalsIgnoreCase(a.getStatus().name()))
                .map(a -> new AlertDTO(a.getId(), a.getUser().getName(), a.getCategory() == null ? null : a.getCategory().getId(), a.getCategory() == null ? null : a.getCategory().getName(), a.getThresholdAmount(), a.getMessage(), a.getActive(), a.getLastTriggeredAt(), a.getStatus().name()))
                .collect(Collectors.toList());
    }

}
