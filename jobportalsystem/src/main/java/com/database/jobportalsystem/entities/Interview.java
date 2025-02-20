package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Interview") // Maps this class to the "Interview" table in the database
public class Interview {

    @Id
    @Column(name = "interviewId", nullable = false, unique = true) // Primary key for the table, must be unique and non-nullable
    private String interviewId;

    // One-to-One relationship with Application
    // Each interview is linked to a specific job application
    @OneToOne
    @JoinColumn(name = "applicationId", nullable = false) // Foreign key to the Application table, cannot be null
    private Application application;

    @Column(name = "date") // Column for the interview date
    private Date date;

    @Column(name = "time") // Column for the interview time
    private Date time;

    @Column(name = "location") // Column for the interview location
    private String location;

    @Column(name = "status", nullable = false) // Column for the interview status (e.g., scheduled, completed)
    private String status;

    @Column(name = "createdAt", nullable = false) // Column for the timestamp when the interview was created (non-nullable)
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the timestamp when the interview was last updated
    private Date updatedAt;

    // Getter for interviewId
    public String getInterviewId() {
        return interviewId;
    }

    // Setter for interviewId
    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    // Getter for application
    public Application getApplication() {
        return application;
    }

    // Setter for application
    public void setApplication(Application application) {
        this.application = application;
    }

    // Getter for date
    public Date getDate() {
        return date;
    }

    // Setter for date
    public void setDate(Date date) {
        this.date = date;
    }

    // Getter for time
    public Date getTime() {
        return time;
    }

    // Setter for time
    public void setTime(Date time) {
        this.time = time;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
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

    // Overriding toString() method for better readability and logging
    @Override
    public String toString() {
        return "Interview [interviewId=" + interviewId + ", application=" + application + ", date=" + date + ", time=" 
                + time + ", location=" + location + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" 
                + updatedAt + "]";
    }

    // Parameterized constructor for initializing all fields
    public Interview(String interviewId, Application application, Date date, Date time, String location, String status,
            Date createdAt, Date updatedAt) {
        super();
        this.interviewId = interviewId;
        this.application = application;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Default constructor for JPA
    public Interview() {
        super();
        // TODO Auto-generated constructor stub
    }
}
