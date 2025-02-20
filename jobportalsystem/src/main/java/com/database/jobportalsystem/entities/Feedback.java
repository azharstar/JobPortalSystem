package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Feedback") // Maps this class to the "Feedback" table in the database
public class Feedback {

    @Id
    @Column(name = "feedbackId", nullable = false, unique = true) // Primary key for the table, must be unique and non-nullable
    private String feedbackId;

    // One-to-One relationship with the Employer
    // Each feedback is linked to a specific employer
    @OneToOne
    @JoinColumn(name = "employerId") // Foreign key referencing the Employer entity
    private Employer employer;

    // One-to-One relationship with the JobSeeker
    // Each feedback is linked to a specific job seeker
    @OneToOne
    @JoinColumn(name = "jobSeekerId") // Foreign key referencing the JobSeeker entity
    private JobSeeker jobSeeker;

    @Column(name = "message", nullable = false) // Column to store the feedback message (non-nullable)
    private String message;

    @Column(name = "createdAt", nullable = false) // Column for the timestamp when the feedback was created (non-nullable)
    private Date createdAt;

    // Getter for feedbackId
    public String getFeedbackId() {
        return feedbackId;
    }

    // Setter for feedbackId
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    // Getter for employer
    public Employer getEmployer() {
        return employer;
    }

    // Setter for employer
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    // Getter for jobSeeker
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    // Setter for jobSeeker
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // Setter for createdAt
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Feedback [feedbackId=" + feedbackId + ", employer=" + employer + ", jobSeeker=" + jobSeeker
                + ", message=" + message + ", createdAt=" + createdAt + "]";
    }

    // Parameterized constructor for initializing all fields
    public Feedback(String feedbackId, Employer employer, JobSeeker jobSeeker, String message, Date createdAt) {
        super();
        this.feedbackId = feedbackId;
        this.employer = employer;
        this.jobSeeker = jobSeeker;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Default constructor for JPA
    public Feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

}
