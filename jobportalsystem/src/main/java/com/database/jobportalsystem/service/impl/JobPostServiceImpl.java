package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.database.jobportalsystem.entities.*;
import com.database.jobportalsystem.service.JobPostService;

public class JobPostServiceImpl implements JobPostService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertJobPost(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        JobPost jobPost = new JobPost();

        try {
            System.out.println("Welcome to Job Post Creation");

            System.out.println("Enter Job Post ID:");
            jobPost.setJobPostId(sc.next());

            System.out.println("Enter Employer ID:");
            String employerId = sc.next();
            Employer employer = session.get(Employer.class, employerId);
            if (employer == null) {
                System.out.println("Employer not found!");
                return;
            }
            jobPost.setEmployer(employer);

            System.out.println("Enter Category ID:");
            String categoryId = sc.next();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                jobPost.setCategory(category);
            }

            System.out.println("Enter Job Title:");
            jobPost.setTitle(sc.next());

            System.out.println("Enter Description:");
            sc.nextLine(); // Consume newline
            jobPost.setDescription(sc.nextLine());

            System.out.println("Enter Location:");
            jobPost.setLocation(sc.next());

            System.out.println("Enter Salary:");
            int salary = getValidIntInput("Enter a valid salary amount:");
            jobPost.setSalary(salary);

            jobPost.setDatePosted(new Date());
            jobPost.setStatus("Open");
            jobPost.setCreatedAt(new Date());
            jobPost.setUpdatedAt(new Date());

            session.persist(jobPost);
            tx.commit();
            System.out.println("Job post inserted successfully!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateJobPost(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Job Post ID to update:");
            String jobPostId = sc.next();
            JobPost jobPost = session.get(JobPost.class, jobPostId);

            if (jobPost == null) {
                System.out.println("Job post not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Title\n2. Update Description\n3. Update Location\n4. Update Salary\n5. Update Status\n6. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Title:");
                        jobPost.setTitle(sc.next());
                        break;
                    case 2:
                        System.out.println("Enter New Description:");
                        sc.nextLine(); // Consume newline
                        jobPost.setDescription(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter New Location:");
                        jobPost.setLocation(sc.next());
                        break;
                    case 4:
                        System.out.println("Enter New Salary:");
                        jobPost.setSalary(getValidIntInput("Enter a valid salary amount:"));
                        break;
                    case 5:
                        System.out.println("Enter New Status (Open/Closed):");
                        jobPost.setStatus(sc.next());
                        break;
                    case 6:
                        System.out.println("Exiting Update...");
                        tx.commit();
                        session.close();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        continue;
                }

                jobPost.setUpdatedAt(new Date());
                session.saveOrUpdate(jobPost);
                tx.commit();
                System.out.println("Job post updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteJobPost(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Job Post ID:");
            String jobPostId = sc.next();
            JobPost jobPost = session.get(JobPost.class, jobPostId);

            if (jobPost != null) {
                session.delete(jobPost);
                tx.commit();
                System.out.println("Job post deleted successfully");
            } else {
                System.out.println("Job post not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllJobPosts(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<JobPost> query = session.createQuery("from JobPost", JobPost.class);
        List<JobPost> jobPosts = query.getResultList();

        for (JobPost job : jobPosts) {
            System.out.println(job);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getJobPost(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Job Post ID:");
        String jobPostId = sc.next();
        JobPost jobPost = session.get(JobPost.class, jobPostId);

        if (jobPost != null) {
            System.out.println(jobPost);
        } else {
            System.out.println("Job post not found!");
        }
        session.close();
    }

    @Override
    public void getJobPostInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(jobPostId) from JobPost", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Job Posts: " + count);
        session.close();
    }

    // Helper method to get valid integer input
    private int getValidIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }
}
