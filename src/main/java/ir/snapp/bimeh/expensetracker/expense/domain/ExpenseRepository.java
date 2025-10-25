package ir.snapp.bimeh.expensetracker.expense.domain;

import ir.snapp.bimeh.expensetracker.expense.application.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    Expense save(Expense expense);

    Optional<Expense> findById(Long id);

    void deleteById(Long id);

    Page<Expense> findAll(Pageable page);

    List<Expense> findAll();

    List<ExpenseCategoryReportDTO> getExpensesGroupByCategoryInDates(Long ownerId, Date start, Date end);

    List<ExpenseCategoryTypeReportDTO> getExpensesGroupByCategoryTypeInDates(Long ownerId, Date start, Date end);

    List<ExpenseDateReportDTO> getExpensesGroupByDailyInDates(Long ownerId, Date start, Date end);

    Double getTotalAmountInDates(Long ownerId, Date start, Date end);

    List<ExpenseCategoryMonthlyComparisonDTO> findCategoryMonthlyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

    List<ExpenseCategoryTypeMonthlyComparisonDTO> findCategoryTypeMonthlyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

    List<ExpenseDateMonthlyComparisonDTO> findDailyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

}
