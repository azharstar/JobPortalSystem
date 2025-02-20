package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Category") // Maps this class to the "Category" table in the database
public class Category {

    @Id
    @Column(name = "categoryId", nullable = false, unique = true) // Primary key for the table, must be unique and non-nullable
    private String categoryId;

    @Column(name = "name", nullable = false) // Column for the name of the category (non-nullable)
    private String name;

    @Column(name = "createdAt", nullable = false) // Column for the timestamp when the category was created (non-nullable)
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the timestamp when the category was last updated
    private Date updatedAt;

    // One-to-Many relationship with JobPostService
    // A category can have multiple job posts
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Cascade all operations to the related JobPostService entities
    private List<JobPost> jobPosts = new ArrayList<>();

    // Getter for categoryId
    public String getCategoryId() {
        return categoryId;
    }

    // Setter for categoryId
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // Setter for createdAt
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter for updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setter for updatedAt
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getter for jobPosts
    public List<JobPost> getJobPosts() {
        return jobPosts;
    }

    // Setter for jobPosts
    public void setJobPosts(List<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" 
                + updatedAt + ", jobPosts=" + jobPosts + "]";
    }

    // Parameterized constructor for initializing all fields
    public Category(String categoryId, String name, Date createdAt, Date updatedAt, List<JobPost> jobPosts) {
        super();
        this.categoryId = categoryId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobPosts = jobPosts;
    }

    // Default constructor for JPA
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

}
