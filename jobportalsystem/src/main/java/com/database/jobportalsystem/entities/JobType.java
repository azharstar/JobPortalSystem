package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "JobType") // Maps this class to the "JobType" table in the database
public class JobType {

    @Id
    @Column(name = "jobTypeId", nullable = false, unique = true) // Primary key for the table
    private String jobTypeId;

    @Column(name = "name", nullable = false) // Non-nullable column for the name of the job type
    private String name;

    @Column(name = "createdAt", nullable = false) // Non-nullable column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // Many-to-Many relationship with JobPostService entity, mapped by the "jobTypes" field in JobPostService
    @ManyToMany(mappedBy = "jobTypes")
    private Set<JobPost> jobPosts = new HashSet<>();

    // Getters and setters for each field
    public String getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(String jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<JobPost> getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "JobType [jobTypeId=" + jobTypeId + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", jobPosts=" + jobPosts + "]";
    }

    // Parameterized constructor for initializing all fields
    public JobType(String jobTypeId, String name, Date createdAt, Date updatedAt, Set<JobPost> jobPosts) {
        super();
        this.jobTypeId = jobTypeId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobPosts = jobPosts;
    }

    // Default constructor for JPA
    public JobType() {
        super();
    }
}
