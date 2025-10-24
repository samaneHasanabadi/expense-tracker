package ir.snapp.bimeh.expensetracker.category.domain;

import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table
@NoArgsConstructor
public class CategoryTemplate extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false , unique = true)
    private CategoryType type;
    @ManyToOne
    private CategoryTemplate parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CategoryTemplate> subTemplateList = new HashSet<>();

    public CategoryTemplate(String name, CategoryType type, CategoryTemplate parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public void assignTypeFromParent() {
        if (parent != null && (type == null || type.equals(CategoryType.OTHER)))
            type = parent.getType();
    }

    public boolean matchesTypeInHierarchy(String type) {
        if (this.type.name().equalsIgnoreCase(type))
            return true;

        CategoryTemplate parent = this.parent;
        while (parent != null) {
            if (parent.getType().name().equalsIgnoreCase(type))
                return true;
            parent = parent.getParent();
        }
        return false;
    }
}
