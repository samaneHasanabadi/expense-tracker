package ir.snapp.bimeh.expensetracker.expense.infrastructure;

import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryTypeReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDateReportDTO;
import ir.snapp.bimeh.expensetracker.expense.domain.Expense;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JpaExpenseRepository extends ExpenseRepository, JpaRepository<Expense, Long> {

    @Query("select new ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseCategoryReportDTO(e.category.id, e.category.name, e.category.type, sum(e.amount)) from Expense e " +
            "where e.owner.id = :ownerId and e.issuedDate between :start and :end " +
            "group by e.category.id, e.category.name, e.category.type")
    List<ExpenseCategoryReportDTO> getExpensesGroupByCategoryInDates(@Param("ownerId") Long ownerId, @Param("start") Date start, @Param("end") Date end);

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
    Double totalAmountInDates(Long ownerId, Date start, Date end);

}
