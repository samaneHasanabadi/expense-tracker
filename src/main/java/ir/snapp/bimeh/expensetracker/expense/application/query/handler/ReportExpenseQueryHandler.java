package ir.snapp.bimeh.expensetracker.expense.application.query.handler;

import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryTypeReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDateReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseMonthlyReportDTO;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportExpenseQueryHandler {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;


    public ExpenseMonthlyReportDTO generateMonthlyReportByCategory(YearMonth yearMonth) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        java.util.Date startDate = Date.from(yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endDate = Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ExpenseCategoryReportDTO> byCategory = expenseRepository.getExpensesGroupByCategoryInDates(currentUser.getId(), startDate, endDate);
        List<ExpenseCategoryTypeReportDTO> byCategoryType = expenseRepository.getExpensesGroupByCategoryTypeInDates(currentUser.getId(), startDate, endDate);
        List<ExpenseDateReportDTO> daily = expenseRepository.getExpensesGroupByDailyInDates(currentUser.getId(), startDate, endDate);
        Double totalAmount = expenseRepository.totalAmountInDates(currentUser.getId(), startDate, endDate);
        return new ExpenseMonthlyReportDTO(currentUser.getName(), totalAmount, yearMonth.getYear(), yearMonth.getMonthValue(), byCategory, byCategoryType, daily);
    }
}
