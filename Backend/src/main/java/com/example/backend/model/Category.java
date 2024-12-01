package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
    @Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;


        @Column(length = 50 , name = "name")
        private String categoryName;
        @Column(name = "description", columnDefinition = "TEXT")
        private String description;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subcategories;

    public long getId() {
        return category_id;
    }

    public void setId(int id) {
        this.category_id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
//Categories (
//        category_id SERIAL PRIMARY KEY,
//        name VARCHAR(50),
//description TEXT,
//parent_category_id INTEGER,
//FOREIGN KEY (parent_category_id) REFERENCES Categories (category_id)
//        );