package ir.snapp.bimeh.expensetracker.category.domain;

import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Table
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ManyToOne
    @Column(nullable = false)
    private User user;
    @ManyToOne
    private CategoryTemplate template;
    @ManyToOne
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Category> subCategories = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    private String description;
}
