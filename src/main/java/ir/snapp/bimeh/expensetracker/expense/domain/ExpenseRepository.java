package ir.snapp.bimeh.expensetracker.expense.domain;

import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryTypeReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDateReportDTO;
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

    Double totalAmountInDates(Long ownerId, Date start, Date end);
}
