package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.database.jobportalsystem.entities.Employer;
import com.database.jobportalsystem.entities.Feedback;
import com.database.jobportalsystem.entities.JobSeeker;
import com.database.jobportalsystem.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertFeedback(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Feedback feedback = new Feedback();

        try {
            System.out.println("Enter Feedback ID:");
            feedback.setFeedbackId(sc.next());

            System.out.println("Enter Employer ID:");
            String employerId = sc.next();
            feedback.setEmployer(session.get(Employer.class, employerId));

            System.out.println("Enter JobSeeker ID:");
            String jobSeekerId = sc.next();
            feedback.setJobSeeker(session.get(JobSeeker.class, jobSeekerId));

            System.out.println("Enter Feedback Message:");
            sc.nextLine(); // Consume newline
            feedback.setMessage(sc.nextLine());

            feedback.setCreatedAt(new Date());

            session.persist(feedback);
            tx.commit();
            System.out.println("Feedback added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateFeedback(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Feedback ID to update:");
            String feedbackId = sc.next();
            Feedback feedback = session.get(Feedback.class, feedbackId);

            if (feedback == null) {
                System.out.println("Feedback not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Message\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Message:");
                        sc.nextLine(); // Consume the newline
                        feedback.setMessage(sc.nextLine());
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

                feedback.setCreatedAt(new Date());  // Updating timestamp for any change
                session.saveOrUpdate(feedback);
                tx.commit();
                System.out.println("Feedback updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteFeedback(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Feedback ID to delete:");
            String feedbackId = sc.next();
            Feedback feedback = session.get(Feedback.class, feedbackId);

            if (feedback != null) {
                session.delete(feedback);
                tx.commit();
                System.out.println("Feedback deleted successfully.");
            } else {
                System.out.println("Feedback not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllFeedbacks(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Feedback> query = session.createQuery("from Feedback", Feedback.class);
        List<Feedback> feedbacks = query.getResultList();

        for (Feedback feedback : feedbacks) {
            System.out.println(feedback);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getFeedback(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Feedback ID:");
        String feedbackId = sc.next();
        Feedback feedback = session.get(Feedback.class, feedbackId);

        if (feedback != null) {
            System.out.println(feedback);
        } else {
            System.out.println("Feedback not found!");
        }
        session.close();
    }

    @Override
    public void getFeedbackInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(feedbackId) from Feedback", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Feedbacks: " + count);
        session.close();
    }
}
