package ir.snapp.bimeh.expensetracker.expense.api;

import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseDTO;
import ir.snapp.bimeh.expensetracker.expense.application.dto.ExpenseMonthlyReportDTO;
import ir.snapp.bimeh.expensetracker.expense.application.query.GetExpensesQuery;
import ir.snapp.bimeh.expensetracker.expense.application.query.handler.GetExpenseQueryHandler;
import ir.snapp.bimeh.expensetracker.expense.application.query.handler.ReportExpenseQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseQueryController {

    private final GetExpenseQueryHandler getExpenseQueryHandler;
    private final ReportExpenseQueryHandler reportExpenseQueryHandler;

    @GetMapping()
    public ResponseEntity<List<ExpenseDTO>> getExpenses(@RequestParam(required = false) Date fromDate,
                                                        @RequestParam(required = false) Date toDate,
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String categoryType,
                                                        @RequestParam(required = false) String paymentMethod,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) throws AccessDeniedException {

        GetExpensesQuery query = new GetExpensesQuery(fromDate, toDate, categoryId, categoryType, paymentMethod, page, size);
        List<ExpenseDTO> expenses = getExpenseQueryHandler.getExpenses(query);

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/report/monthly/{yearMonth}")
    public ResponseEntity<ExpenseMonthlyReportDTO> generateMonthlyReportByCategory(@PathVariable YearMonth yearMonth) throws AccessDeniedException {
        return ResponseEntity.ok(reportExpenseQueryHandler.generateMonthlyReportByCategory(yearMonth));
    }
}
