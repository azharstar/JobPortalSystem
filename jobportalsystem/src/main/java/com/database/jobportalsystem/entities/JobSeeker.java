package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "JobSeeker") // Maps this class to the "JobSeeker" table in the database
public class JobSeeker {

    @Id
    @Column(name = "jobSeekerId", nullable = false, unique = true) // Primary key for the table
    private String jobSeekerId;

    @Column(name = "name", nullable = false) // Non-nullable column for the job seeker's name
    private String name;

    @Column(name = "email", nullable = false, unique = true) // Non-nullable and unique column for email
    private String email;

    @Column(name = "password", nullable = false) // Non-nullable column for the job seeker's password
    private String password;

    @Column(name = "phone", nullable = false, unique = true) // Changed type from double to String
    private String phone;

    @Column(name = "createdAt", nullable = false) // Non-nullable column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // Many-to-Many relationship between JobSeeker and Skill entities
    @ManyToMany
    @JoinTable(
        name = "JobSeeker_Skill", // Join table name
        joinColumns = @JoinColumn(name = "jobSeekerId"), // Foreign key referencing JobSeeker
        inverseJoinColumns = @JoinColumn(name = "skillId") // Foreign key referencing Skill
    )
    private Set<Skill> skills = new HashSet<>();

    // One-to-One relationship with Resume entity, with cascading all operations
    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Resume resume;

    // One-to-Many relationship with Application entity, with cascading all operations
    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();

    // Many-to-Many relationship between JobSeeker and Role entities
    @ManyToMany
    @JoinTable(
        name = "JobSeeker_Role", // Join table name
        joinColumns = @JoinColumn(name = "jobSeekerId"), // Foreign key referencing JobSeeker
        inverseJoinColumns = @JoinColumn(name = "roleId") // Foreign key referencing Role
    )
    private Set<Role> roles = new HashSet<>();

    // Parameterized constructor
    public JobSeeker(String jobSeekerId, String name, String email, String password, String phone, Date createdAt,
                     Date updatedAt, Set<Skill> skills, Resume resume, List<Application> applications, Set<Role> roles) {
        super();
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.skills = skills;
        this.resume = resume;
        this.applications = applications;
        this.roles = roles;
    }

    // Default constructor for JPA
    public JobSeeker() {
        super();
    }

    // Getters and setters
    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() { // Changed return type from double to String
        return phone;
    }

    public void setPhone(String phone) { // Changed parameter type from double to String
        this.phone = phone;
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
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
        return "\n--------JobSeeker---------"
                + "\n Job Seeker Id : " + jobSeekerId +
                "\n Name : " + name +
                "\n Email : " + email +
                "\n Password : " + password +
                "\n Phone : " + phone +
                "\n Created At : " + createdAt +
                "\n Updated At : " + updatedAt +
                "\n Skills : " + skills +
                "\n Resume=" + resume +
                "\n Applications : " + applications +
                "\n Roles : " + roles +
                "\n---------------------------------------";
    }
}
