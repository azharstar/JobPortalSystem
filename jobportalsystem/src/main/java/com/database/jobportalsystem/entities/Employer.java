package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Employer") // Maps this class to the "Employer" table in the database
public class Employer {

    @Id
    @Column(name = "employerId", nullable = false, unique = true) // Primary key for the table
    private String employerId;

    @Column(name = "companyName", nullable = false) // Non-nullable column for the employer's company name
    private String companyName;

    @Column(name = "email", nullable = false, unique = true) // Non-nullable and unique column for email
    private String email;

    @Column(name = "password", nullable = false) // Non-nullable column for the employer's password
    private String password;

    @Column(name = "phone", nullable = false, unique = true) // Non-nullable and unique column for phone number
    private String phone;

    @Column(name = "address") // Column for the employer's address (optional)
    private String address;

    @Column(name = "createdAt", nullable = false) // Non-nullable column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // One-to-Many relationship with JobPostService entity
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<JobPost> jobPosts = new ArrayList<>();

    // One-to-One relationship with Feedback entity
    @OneToOne(mappedBy = "employer", cascade = CascadeType.ALL)
    private Feedback feedback;

    // Many-to-Many relationship between Employer and Role entities
    @ManyToMany
    @JoinTable(
        name = "Employer_Role", // Join table name
        joinColumns = @JoinColumn(name = "employerId"), // Foreign key referencing Employer
        inverseJoinColumns = @JoinColumn(name = "roleId") // Foreign key referencing Role
    )
    private Set<Role> roles = new HashSet<>();

    // Getters and setters for each field
    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<JobPost> getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(List<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Employer [employerId=" + employerId + ", companyName=" + companyName + ", email=" + email
                + ", password=" + password + ", phone=" + phone + ", address=" + address + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", jobPosts=" + jobPosts + ", feedback=" + feedback + ", roles=" + roles
                + "]";
    }

    // Parameterized constructor for initializing all fields
    public Employer(String employerId, String companyName, String email, String password, String phone, String address,
                    Date createdAt, Date updatedAt, List<JobPost> jobPosts, Feedback feedback, Set<Role> roles) {
        super();
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobPosts = jobPosts;
        this.feedback = feedback;
        this.roles = roles;
    }

    // Default constructor for JPA
    public Employer() {
        super();
    }
}
