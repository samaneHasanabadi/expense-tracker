package ir.snapp.bimeh.expensetracker.expense.domain;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Expense extends BaseEntity {

    private String title;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Date issuedDate;
    @ManyToOne
    @JoinColumn(name= "ownerId", nullable = false)
    private User owner;
    @ManyToOne
    @JoinColumn(name= "categoryId")
    private Category category;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String description;
}
