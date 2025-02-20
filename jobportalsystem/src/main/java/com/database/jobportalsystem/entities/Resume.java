package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Resume") // Maps this class to the "Resume" table in the database
public class Resume {

    @Id
    @Column(name = "resumeId", nullable = false, unique = true) // Primary key for the table
    private String resumeId;

    // One-to-One relationship with JobSeeker entity
    // Each resume is associated with one job seeker
    @OneToOne
    @JoinColumn(name = "jobSeekerId", nullable = false) // Foreign key column "jobSeekerId"
    private JobSeeker jobSeeker;

    @Column(name = "filePath", nullable = false) // Non-nullable column for the file path of the resume
    private String filePath;

    @Column(name = "createdAt", nullable = false) // Non-nullable column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // Getter for resumeId
    public String getResumeId() {
        return resumeId;
    }

    // Setter for resumeId
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    // Getter for jobSeeker
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    // Setter for jobSeeker
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    // Getter for filePath
    public String getFilePath() {
        return filePath;
    }

    // Setter for filePath
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Resume [resumeId=" + resumeId + ", jobSeeker=" + jobSeeker + ", filePath=" + filePath + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    // Parameterized constructor for initializing all fields
    public Resume(String resumeId, JobSeeker jobSeeker, String filePath, Date createdAt, Date updatedAt) {
        super();
        this.resumeId = resumeId;
        this.jobSeeker = jobSeeker;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Default constructor for JPA
    public Resume() {
        super();
        // TODO Auto-generated constructor stub
    }
}
