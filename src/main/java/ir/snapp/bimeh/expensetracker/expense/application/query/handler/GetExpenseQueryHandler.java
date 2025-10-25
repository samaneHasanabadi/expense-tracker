package ir.snapp.bimeh.expensetracker.expense.application.query.handler;

import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDTO;
import ir.snapp.bimeh.expensetracker.expense.application.query.GetExpensesQuery;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
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
public class GetExpenseQueryHandler {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public List<ExpenseDTO> getExpenses(GetExpensesQuery query) throws AccessDeniedException {
        var currentUser = authenticatedUserProvider.getCurrentUser();
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return expenseRepository.findAll(pageable).stream()
                .filter(e -> e.getOwner().getId().equals(currentUser.getId()))
                .filter(e -> query.categoryId() == null || e.getCategory().matchesCategoryInHierarchy(query.categoryId(), true))
                .filter(e -> query.categoryType() == null || e.getCategory().matchesTypeInHierarchy(query.categoryType()))
                .filter(e -> query.toDate() == null || e.getIssuedDate().before(query.toDate()))
                .filter(e -> query.fromDate() == null || e.getIssuedDate().after(query.fromDate()))
                .map(e -> new ExpenseDTO(e.getId(), e.getTitle(), e.getAmount(),
                        e.getIssuedDate(), e.getCategory() == null ? null : e.getCategory().getName(), e.getCategory() == null ? null : e.getCategory().getTemplate().getName(), e.getPaymentMethod() == null ? null : e.getPaymentMethod().toString(), e.getDescription(), e.getOwner().toString()))
                .collect(Collectors.toList());
    }

}
