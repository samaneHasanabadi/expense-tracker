package ir.snapp.bimeh.expensetracker.alert.domain;

import ir.snapp.bimeh.expensetracker.category.domain.Category;
import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Alert extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Double thresholdAmount;
    @Column(nullable = false)
    private Boolean active;
    private Date lastTriggeredAt;

}
