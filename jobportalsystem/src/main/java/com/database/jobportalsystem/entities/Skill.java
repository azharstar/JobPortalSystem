package com.database.jobportalsystem.entities;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Skill") // Maps this class to the "Skill" table in the database
public class Skill {

    @Id
    @Column(name = "skillId", nullable = false, unique = true) // Primary key for the table, must be unique and non-nullable
    private String skillId;

    @Column(name = "name") // Column for the skill's name
    private String name;

    @Column(name = "createdAt", nullable = false) // Column for the timestamp when the skill was created
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the timestamp when the skill was last updated
    private Date updatedAt;

    // Many-to-Many relationship with JobSeeker
    // A skill can be associated with multiple job seekers
    @ManyToMany(mappedBy = "skills") // The 'skills' attribute in the JobSeeker entity maps this relationship
    private Set<JobSeeker> jobSeekers = new HashSet<>();

    // Many-to-Many relationship with JobPostService
    // A skill can be associated with multiple job posts
    @ManyToMany(mappedBy = "skills") // The 'skills' attribute in the JobPostService entity maps this relationship
    private Set<JobPost> jobPosts = new HashSet<>();

    // Getter for skillId
    public String getSkillId() {
        return skillId;
    }

    // Setter for skillId
    public void setSkillId(String skillId) {
        this.skillId = skillId;
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

    // Getter for jobPosts
    public Set<JobPost> getJobPosts() {
        return jobPosts;
    }

    // Setter for jobPosts
    public void setJobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "Skill [skillId=" + skillId + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", jobSeekers=" + jobSeekers + ", jobPosts=" + jobPosts + "]";
    }

    // Parameterized constructor for initializing all fields
    public Skill(String skillId, String name, Date createdAt, Date updatedAt, Set<JobSeeker> jobSeekers,
            Set<JobPost> jobPosts) {
        super();
        this.skillId = skillId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobSeekers = jobSeekers;
        this.jobPosts = jobPosts;
    }

    // Default constructor for JPA
    public Skill() {
        super();
        // TODO Auto-generated constructor stub
    }

}
