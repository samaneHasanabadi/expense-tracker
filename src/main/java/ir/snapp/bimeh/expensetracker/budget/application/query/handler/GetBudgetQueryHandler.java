package ir.snapp.bimeh.expensetracker.budget.application.query.handler;

import ir.snapp.bimeh.expensetracker.budget.application.dto.BudgetDTO;
import ir.snapp.bimeh.expensetracker.budget.application.query.GetBudgetsQuery;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
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
public class GetBudgetQueryHandler {

    private final BudgetRepository budgetRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public List<BudgetDTO> handle(GetBudgetsQuery query) throws AccessDeniedException {
        var currentUser = authenticatedUserProvider.getCurrentUser();
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return budgetRepository.findAll(pageable).stream()
                .filter(b -> b.getUser().getId().equals(currentUser.getId()))
                .filter(b -> query.categoryId() == null || b.getCategory() != null && b.getCategory().matchesCategoryInHierarchy(query.categoryId(), true))
                .filter(b -> query.categoryType() == null || b.getCategory() != null && b.getCategory().matchesTypeInHierarchy(query.categoryType()))
                .filter(b -> query.active() == null || b.getActive().equals(query.active()))
                .map(b -> new BudgetDTO(b.getId(), b.getUser().getName(), b.getCategory() == null ? null : b.getCategory().getId(), b.getCategory() == null ? null : b.getCategory().getName(), b.getMonthlyLimit(), b.getAlertThresholdPercentage(), b.getActive()))
                .collect(Collectors.toList());
    }

}
