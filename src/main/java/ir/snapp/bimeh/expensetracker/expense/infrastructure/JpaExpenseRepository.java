package ir.snapp.bimeh.expensetracker.expense.infrastructure;

import ir.snapp.bimeh.expensetracker.expense.application.dto.*;
import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JpaExpenseRepository extends ExpenseRepository, JpaRepository<Expense, Long> {

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryReportDTO(e.category.id, e.category.name, e.category.type, sum(e.amount)) from Expense e " +
            "where e.owner.id = :ownerId and e.issuedDate between :start and :end " +
            "group by e.category.id, e.category.name, e.category.type")
    List<ExpenseCategoryReportDTO> getExpensesGroupByCategoryInDates(Long ownerId, Date start, Date end);

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryTypeReportDTO(e.category.type, sum(e.amount)) from Expense e " +
            "where e.owner.id = :ownerId and e.issuedDate between :start and :end " +
            "group by e.category.type")
    List<ExpenseCategoryTypeReportDTO> getExpensesGroupByCategoryTypeInDates(Long ownerId, Date start, Date end);

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDateReportDTO(cast(e.issuedDate as date), sum(e.amount)) from Expense e " +
            "where e.owner.id = :ownerId and e.issuedDate between :start and :end " +
            "group by cast(e.issuedDate as date) ")
    List<ExpenseDateReportDTO> getExpensesGroupByDailyInDates(Long ownerId, Date start, Date end);

    @Query("select sum(e.amount) from Expense e " +
            "where e.owner.id = :ownerId and e.issuedDate between :start and :end")
    Double getTotalAmountInDates(Long ownerId, Date start, Date end);

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryMonthlyComparisonDTO" +
            "(e.category.id, e.category.name, e.category.type, " +
            "sum(case when e.issuedDate between :currentStart and :currentEnd then e.amount else 0l end), " +
            "sum(case when e.issuedDate between :previousStart and :previousEnd then e.amount else 0l end)) from Expense e " +
            "where e.owner.id = :ownerId " +
            "group by e.category.id, e.category.name, e.category.type")
    List<ExpenseCategoryMonthlyComparisonDTO> findCategoryMonthlyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryTypeMonthlyComparisonDTO(" +
            "e.category.type, " +
            "sum(case when e.issuedDate between :currentStart and :currentEnd then e.amount else 0l end), " +
            "sum(case when e.issuedDate between :previousStart and :previousEnd then e.amount else 0l end)) from Expense e " +
            "where e.owner.id = :ownerId " +
            "group by e.category.type")
    List<ExpenseCategoryTypeMonthlyComparisonDTO> findCategoryTypeMonthlyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDateMonthlyComparisonDTO(" +
            "cast(e.issuedDate as date), " +
            "sum(case when e.issuedDate between :currentStart and :currentEnd then e.amount else 0l end), " +
            "sum(case when e.issuedDate between :previousStart and :previousEnd then e.amount else 0l end)) from Expense e " +
            "where e.owner.id = :ownerId " +
            "group by cast(e.issuedDate as date) ")
    List<ExpenseDateMonthlyComparisonDTO> findDailyComparison(Long ownerId, Date currentStart, Date currentEnd, Date previousStart, Date previousEnd);

}
