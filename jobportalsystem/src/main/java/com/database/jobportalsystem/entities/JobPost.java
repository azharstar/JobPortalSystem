package com.database.jobportalsystem.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "JobPostService") // Maps this class to the "JobPostService" table in the database
public class JobPost {

    @Id
    @Column(name = "jobPostId", nullable = false, unique = true) // Primary key for the table
    private String jobPostId;

    // Many-to-One relationship with Employer entity
    // Each job post is associated with one employer
    @ManyToOne
    @JoinColumn(name = "employerId", nullable = false) // Foreign key column "employerId"
    private Employer employer;

    // Many-to-One relationship with Category entity
    // Each job post can belong to one category
    @ManyToOne
    @JoinColumn(name = "categoryId") // Foreign key column "categoryId"
    private Category category;

    @Column(name = "title", nullable = false) // Non-nullable column for the job post title
    private String title;

    @Column(name = "description") // Column for the job description
    private String description;

    @Column(name = "location") // Column for the job location
    private String location;

    @Column(name = "salary", nullable = false) // Non-nullable column for the job salary
    private int salary;

    @Column(name = "datePosted") // Column for the job posting date
    private Date datePosted;

    @Column(name = "status") // Column for the job status (e.g., open, closed)
    private String status;

    @Column(name = "createdAt") // Column for the creation timestamp
    private Date createdAt;

    @Column(name = "updatedAt") // Column for the last update timestamp
    private Date updatedAt;

    // Many-to-Many relationship with Skill entity
    // Maps to the "JobPost_Skill" join table
    @ManyToMany
    @JoinTable(
        name = "JobPost_Skill", // Name of the join table
        joinColumns = @JoinColumn(name = "jobPostId"), // Foreign key in the join table pointing to JobPostService
        inverseJoinColumns = @JoinColumn(name = "skillId") // Foreign key in the join table pointing to Skill
    )
    private Set<Skill> skills = new HashSet<>();

    // Many-to-Many relationship with JobType entity
    // Maps to the "JobPost_JobType" join table
    @ManyToMany
    @JoinTable(
        name = "JobPost_JobType", // Name of the join table
        joinColumns = @JoinColumn(name = "jobPostId"), // Foreign key in the join table pointing to JobPostService
        inverseJoinColumns = @JoinColumn(name = "jobTypeId") // Foreign key in the join table pointing to JobType
    )
    private Set<JobType> jobTypes = new HashSet<>();

    // One-to-Many relationship with Application entity
    // Each job post can have multiple applications
    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();

    // Getters and setters for all fields
    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<JobType> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(Set<JobType> jobTypes) {
        this.jobTypes = jobTypes;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    // Overriding toString() method for better readability
    @Override
    public String toString() {
        return "JobPostService [jobPostId=" + jobPostId + ", employer=" + employer + ", category=" + category + ", title="
                + title + ", description=" + description + ", location=" + location + ", salary=" + salary
                + ", datePosted=" + datePosted + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", skills=" + skills + ", jobTypes=" + jobTypes + ", applications=" + applications + "]";
    }

    // Parameterized constructor for initializing all fields
    public JobPost(String jobPostId, Employer employer, Category category, String title, String description,
            String location, int salary, Date datePosted, String status, Date createdAt, Date updatedAt,
            Set<Skill> skills, Set<JobType> jobTypes, List<Application> applications) {
        super();
        this.jobPostId = jobPostId;
        this.employer = employer;
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.datePosted = datePosted;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.skills = skills;
        this.jobTypes = jobTypes;
        this.applications = applications;
    }

    // Default constructor for JPA
    public JobPost() {
        super();
    }
}
