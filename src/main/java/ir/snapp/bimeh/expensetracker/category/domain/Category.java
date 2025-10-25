package ir.snapp.bimeh.expensetracker.category.domain;

import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.common.exception.InvalidCategoryHierarchyException;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner;
    @ManyToOne
    @JoinColumn(name = "templateId", nullable = false)
    private CategoryTemplate template;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Category parent;
    private String description;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Category> subCategories = new HashSet<>();

    public void setParent(Category parent) {
        if (this.equals(parent)) {
            throw new InvalidCategoryHierarchyException("A category cannot be its own parent");
        }
        if (parent != null && parent.hasAncestor(this)) {
            throw new InvalidCategoryHierarchyException("Circular category hierarchy detected");
        }
        this.parent = parent;
    }

    public boolean hasAncestor(Category category) {
        if (parent == null)
            return false;
        if (parent.equals(category))
            return true;
        return parent.hasAncestor(category);
    }

    public void assignTemplateFromParent() {
        if (parent != null && (template == null || template.getType().equals(CategoryType.OTHER))) {
            template = parent.getTemplate();
            type = parent.getTemplate().getType();
        }
        if (type == null)
            type = template.getType();
    }

    public boolean matchesTypeInHierarchy(String type) {
        if (this.type.name().equalsIgnoreCase(type))
            return true;

        Category parent = this.parent;
        while (parent != null) {
            if (parent.getType().name().equalsIgnoreCase(type))
                return true;
            parent = parent.getParent();
        }
        return false;
    }

    public boolean matchesCategoryInHierarchy(Long categoryId) {
        if (this.getId().equals(categoryId))
            return true;

        Category parent = this.parent;
        while (parent != null) {
            if (parent.getId().equals(categoryId))
                return true;
            parent = parent.getParent();
        }
        return false;
    }
}
