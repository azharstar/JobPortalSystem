package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.database.jobportalsystem.entities.*;
import com.database.jobportalsystem.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertApplication(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Application application = new Application();

        try {
            System.out.println("Welcome to Application Submission");

            System.out.println("Enter Application ID:");
            application.setApplicationId(sc.next());

            System.out.println("Enter Job Post ID:");
            String jobPostId = sc.next();
            JobPost jobPost = session.get(JobPost.class, jobPostId);
            if (jobPost == null) {
                System.out.println("Job Post not found!");
                return;
            }
            application.setJobPost(jobPost);

            System.out.println("Enter Job Seeker ID:");
            String jobSeekerId = sc.next();
            JobSeeker jobSeeker = session.get(JobSeeker.class, jobSeekerId);
            if (jobSeeker == null) {
                System.out.println("Job Seeker not found!");
                return;
            }
            application.setJobSeeker(jobSeeker);

            application.setDateApplied(new Date());
            application.setStatus("Pending");
            application.setCreatedAt(new Date());
            application.setUpdatedAt(new Date());

            session.persist(application);
            tx.commit();
            System.out.println("Application submitted successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateApplication(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Application ID to update:");
            String applicationId = sc.next();
            Application application = session.get(Application.class, applicationId);

            if (application == null) {
                System.out.println("Application not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Status\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Status (Pending/Accepted/Rejected):");
                        application.setStatus(sc.next());
                        break;
                    case 2:
                        System.out.println("Exiting Update...");
                        tx.commit();
                        session.close();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        continue;
                }

                application.setUpdatedAt(new Date());
                session.saveOrUpdate(application);
                tx.commit();
                System.out.println("Application updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteApplication(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Application ID:");
            String applicationId = sc.next();
            Application application = session.get(Application.class, applicationId);

            if (application != null) {
                session.delete(application);
                tx.commit();
                System.out.println("Application deleted successfully");
            } else {
                System.out.println("Application not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllApplications(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Application> query = session.createQuery("from Application", Application.class);
        List<Application> applications = query.getResultList();

        for (Application app : applications) {
            System.out.println(app);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getApplication(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Application ID:");
        String applicationId = sc.next();
        Application application = session.get(Application.class, applicationId);

        if (application != null) {
            System.out.println(application);
        } else {
            System.out.println("Application not found!");
        }
        session.close();
    }

    @Override
    public void getApplicationInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(applicationId) from Application", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Applications: " + count);
        session.close();
    }
}
