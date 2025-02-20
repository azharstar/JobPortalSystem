package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Application") // Maps this class to the "Application" table in the database
public class Application {

    @Id
    @Column(name = "applicationId", nullable = false, unique = true) // Primary key for the table
    private String applicationId;

    // Many-to-One relationship with JobPostService
    // Each application is associated with one job post
    @ManyToOne
    @JoinColumn(name = "jobPostId", nullable = false) // Foreign key column "jobPostId"
    private JobPost jobPost;

    // Many-to-One relationship with JobSeeker
    // Each application is associated with one job seeker
    @ManyToOne
    @JoinColumn(name = "jobSeekerId", nullable = false) // Foreign key column "jobSeekerId"
    private JobSeeker jobSeeker;

    @Column(name = "status", nullable = false) // Non-nullable column to track the status of the application
    private String status;

    @Column(name = "dateApplied", nullable = false) // Non-nullable column for the date the application was made
    private Date dateApplied;

    @Column(name = "createdAt", nullable = false) // Non-nullable column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // One-to-One relationship with Interview
    // Each application can have one associated interview
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL) // Bidirectional mapping
    private Interview interview;

    // Getter for applicationId
    public String getApplicationId() {
        return applicationId;
    }

    // Setter for applicationId
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    // Getter for jobPost
    public JobPost getJobPost() {
        return jobPost;
    }

    // Setter for jobPost
    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    // Getter for jobSeeker
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    // Setter for jobSeeker
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for dateApplied
    public Date getDateApplied() {
        return dateApplied;
    }

    // Setter for dateApplied
    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
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

    // Getter for interview
    public Interview getInterview() {
        return interview;
    }

    // Setter for interview
    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Application [applicationId=" + applicationId + ", jobPost=" + jobPost + ", jobSeeker=" + jobSeeker
                + ", status=" + status + ", dateApplied=" + dateApplied + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", interview=" + interview + "]";
    }

    // Parameterized constructor for initializing all fields
    public Application(String applicationId, JobPost jobPost, JobSeeker jobSeeker, String status, Date dateApplied,
            Date createdAt, Date updatedAt, Interview interview) {
        super();
        this.applicationId = applicationId;
        this.jobPost = jobPost;
        this.jobSeeker = jobSeeker;
        this.status = status;
        this.dateApplied = dateApplied;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.interview = interview;
    }

    // Default constructor for JPA
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }
}
