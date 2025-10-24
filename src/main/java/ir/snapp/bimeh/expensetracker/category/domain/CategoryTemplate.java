package ir.snapp.bimeh.expensetracker.category.domain;

import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table
public class CategoryTemplate extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    private CategoryTemplate parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CategoryTemplate> subTemplateList = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private CategoryType type;

}
