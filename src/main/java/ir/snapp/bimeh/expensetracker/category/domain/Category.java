package ir.snapp.bimeh.expensetracker.category.domain;

import ir.snapp.bimeh.expensetracker.common.domain.BaseEntity;
import ir.snapp.bimeh.expensetracker.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Table
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "templateId", nullable = false)
    private CategoryTemplate template;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Category parent;
    private String description;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Category> subCategories = new HashSet<>();

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
