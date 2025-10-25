package ir.snapp.bimeh.expensetracker.expense.application.query.handler;

import ir.snapp.bimeh.expensetracker.expense.application.dto.*;
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


    public ExpenseMonthlyReportDTO generateMonthlyReport(YearMonth yearMonth) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        java.util.Date startDate = Date.from(yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date endDate = Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ExpenseCategoryReportDTO> byCategory = expenseRepository.getExpensesGroupByCategoryInDates(currentUser.getId(), startDate, endDate);
        List<ExpenseCategoryTypeReportDTO> byCategoryType = expenseRepository.getExpensesGroupByCategoryTypeInDates(currentUser.getId(), startDate, endDate);
        List<ExpenseDateReportDTO> daily = expenseRepository.getExpensesGroupByDailyInDates(currentUser.getId(), startDate, endDate);
        Double totalAmount = expenseRepository.getTotalAmountInDates(currentUser.getId(), startDate, endDate);
        return new ExpenseMonthlyReportDTO(currentUser.getName(), totalAmount, yearMonth.getYear(), yearMonth.getMonthValue(), byCategory, byCategoryType, daily);
    }

    public ExpenseMonthlyComparisonReportDTO generateMonthlyComparisonReport(YearMonth currentYearMonth, YearMonth previousYearMonth) throws AccessDeniedException {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        java.util.Date currentStartDate = Date.from(currentYearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date currentEndDate = Date.from(currentYearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date previousStartDate = Date.from(previousYearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date previosEndDate = Date.from(previousYearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ExpenseCategoryMonthlyComparisonDTO> byCategoryComparison = expenseRepository.findCategoryMonthlyComparison(currentUser.getId(), currentStartDate, currentEndDate, previousStartDate, previosEndDate);
        List<ExpenseCategoryTypeMonthlyComparisonDTO> byCategoryTypeComparison = expenseRepository.findCategoryTypeMonthlyComparison(currentUser.getId(), currentStartDate, currentEndDate, previousStartDate, previosEndDate);
        List<ExpenseDateMonthlyComparisonDTO> dailyComparison = expenseRepository.findDailyComparison(currentUser.getId(), currentStartDate, currentEndDate, previousStartDate, previosEndDate);
        Double totalCurrentAmount = expenseRepository.getTotalAmountInDates(currentUser.getId(), currentStartDate, currentEndDate);
        Double totalPreviousAmount = expenseRepository.getTotalAmountInDates(currentUser.getId(), previousStartDate, previosEndDate);
        return new ExpenseMonthlyComparisonReportDTO(currentUser.getName(), currentYearMonth.getYear(), currentYearMonth.getMonthValue(), previousYearMonth.getYear(), previousYearMonth.getMonthValue(), totalCurrentAmount, totalPreviousAmount, totalCurrentAmount - totalPreviousAmount, byCategoryComparison, byCategoryTypeComparison, dailyComparison);
    }
}
