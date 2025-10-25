package ir.snapp.bimeh.expensetracker.budget.domain;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Budget extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @Column(nullable = false)
    private Double monthlyLimit;
    @Column(nullable = false)
    private Integer alertThresholdPercentage;
    @Column(nullable = false)
    private Boolean active;

}
