package com.database.jobportalsystem.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Role") // Maps this class to the "Role" table in the database
public class Role {

    @Id
    @Column(name = "roleId", nullable = false, unique = true) // Primary key for the table, must be unique and non-nullable
    private String roleId;

    @Column(name = "name", nullable = false) // Column for the role name (e.g., "Admin", "Job Seeker")
    private String name;

    @Column(name = "createdAt", nullable = false) // Column for the timestamp when the role was created
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the timestamp when the role was last updated
    private Date updatedAt;

    // Many-to-Many relationship with JobSeeker
    // A role can be associated with multiple job seekers
    @ManyToMany(mappedBy = "roles") // The 'roles' attribute in the JobSeeker entity maps this relationship
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    // Many-to-Many relationship with Employer (employer)
    // A role can be associated with multiple employers
    @ManyToMany(mappedBy = "roles") // The 'roles' attribute in the Employer entity maps this relationship
    private Set<Employer> employers = new HashSet<>();

    // Getter for roleId
    public String getRoleId() {
        return roleId;
    }

    // Setter for roleId
    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    // Getter for jobSeekers
    public Set<JobSeeker> getJobSeekers() {
        return jobSeekers;
    }

    // Setter for jobSeekers
    public void setJobSeekers(Set<JobSeeker> jobSeekers) {
        this.jobSeekers = jobSeekers;
    }

    // Getter for employers
    public Set<Employer> getEmployers() {
        return employers;
    }

    // Setter for employers
    public void setEmployers(Set<Employer> employers) {
        this.employers = employers;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", jobSeekers=" + jobSeekers + ", employers=" + employers + "]";
    }

    // Parameterized constructor for initializing all fields
    public Role(String roleId, String name, Date createdAt, Date updatedAt, Set<JobSeeker> jobSeekers,
            Set<Employer> employers) {
        super();
        this.roleId = roleId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobSeekers = jobSeekers;
        this.employers = employers;
    }

    // Default constructor for JPA
    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

}
