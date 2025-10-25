package ir.snapp.bimeh.expensetracker.alert.application.scheduler;

import ir.snapp.bimeh.expensetracker.alert.domain.Alert;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertRepository;
import ir.snapp.bimeh.expensetracker.alert.domain.AlertStatus;
import ir.snapp.bimeh.expensetracker.budget.domain.Budget;
import ir.snapp.bimeh.expensetracker.budget.domain.BudgetRepository;
import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.expense.domain.ExpenseRepository;
import ir.snapp.bimeh.expensetracker.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AlertScheduler {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "${app.alert.scheduler-timing}")
    public void createAlert() {
        userRepository.findAll().forEach(user -> {
            List<Budget> budgetList = budgetRepository.findByUserAndActive(user, true);
            for (Budget budget : budgetList) {
                List<Long> categoryIdList = new ArrayList<>();
                collectIds(budget.getCategory(), categoryIdList);
                LocalDate now = LocalDate.now();
                LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
                Date startDate = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
                double thresholdAmount = budget.getMonthlyLimit() * budget.getAlertThresholdPercentage() / 100;
                Double spentInMonth;
                if (budget.getCategory() != null)
                    spentInMonth = expenseRepository.getTotalAmountByCategoryInDates(user.getId(), categoryIdList, startDate, new Date());
                else
                    spentInMonth = expenseRepository.getTotalAmountInDates(user.getId(), startDate, new Date());
                if (spentInMonth == null)
                    continue;
                Integer spentPercentage = (int) (spentInMonth / budget.getMonthlyLimit() * 100);
                if (spentInMonth > thresholdAmount) {
                    alertRepository.deactivateAlerts(AlertStatus.CREATED, user, budget.getCategory());
                    Alert alert = new Alert();
                    alert.setUser(user);
                    alert.setCategory(budget.getCategory());
                    alert.setThresholdAmount(spentInMonth);
                    alert.setActive(true);
                    alert.setStatus(AlertStatus.CREATED);
                    String categoryPart = budget.getCategory() != null
                            ? "category " + budget.getCategory().getName()
                            : "this month";
                    String message = String.format(
                            "You’ve spent %.2f (%d%%) in %s. Your budget for this period is %.2f.",
                            spentInMonth,
                            spentPercentage,
                            categoryPart,
                            budget.getMonthlyLimit()
                    );
                    alert.setMessage(message);
                    alertRepository.save(alert);

                }
            }
        });
    }

    private void collectIds(Category category, List<Long> ids) {
        if (category == null)
            return;
        ids.add(category.getId());
        Set<Category> children = category.getSubCategories();
        children.forEach(child -> collectIds(child, ids));
    }
}
