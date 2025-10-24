package ir.snapp.bimeh.expensetracker.expense.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Long id);

    void deleteById(Long id);

    Page<Expense> findAll(Pageable page);

    List<Expense> findAll();

}
