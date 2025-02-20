package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.Interview;
import com.database.jobportalsystem.entities.Application;
import com.database.jobportalsystem.service.InterviewService;

public class InterviewServiceImpl implements InterviewService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertInterview(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Interview interview = new Interview();

        try {
            System.out.println("Enter Interview ID:");
            interview.setInterviewId(sc.next());

            System.out.println("Enter Application ID (to link interview):");
            String applicationId = sc.next();
            Application application = session.get(Application.class, applicationId);
            if (application != null) {
                interview.setApplication(application);
            } else {
                System.out.println("Application not found!");
                return;
            }

            System.out.println("Enter Interview Date (YYYY-MM-DD):");
            String dateInput = sc.next();
            interview.setDate(java.sql.Date.valueOf(dateInput));

            System.out.println("Enter Interview Time (HH:MM:SS):");
            String timeInput = sc.next();
            interview.setTime(java.sql.Time.valueOf(timeInput));

            System.out.println("Enter Interview Location:");
            interview.setLocation(sc.next());

            System.out.println("Enter Interview Status (scheduled/completed/etc.):");
            interview.setStatus(sc.next());

            interview.setCreatedAt(new Date());
            interview.setUpdatedAt(new Date());

            session.persist(interview);
            tx.commit();
            System.out.println("Interview added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateInterview(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Interview ID to update:");
            String interviewId = sc.next();
            Interview interview = session.get(Interview.class, interviewId);

            if (interview == null) {
                System.out.println("Interview not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Date\n2. Update Time\n3. Update Location\n4. Update Status\n5. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Date (YYYY-MM-DD):");
                        String newDate = sc.next();
                        interview.setDate(java.sql.Date.valueOf(newDate));
                        break;
                    case 2:
                        System.out.println("Enter New Time (HH:MM:SS):");
                        String newTime = sc.next();
                        interview.setTime(java.sql.Time.valueOf(newTime));
                        break;
                    case 3:
                        System.out.println("Enter New Location:");
                        interview.setLocation(sc.next());
                        break;
                    case 4:
                        System.out.println("Enter New Status:");
                        interview.setStatus(sc.next());
                        break;
                    case 5:
                        System.out.println("Exiting Update...");
                        tx.commit();
                        session.close();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        continue;
                }

                interview.setUpdatedAt(new Date());  // Updating timestamp for any change
                session.saveOrUpdate(interview);
                tx.commit();
                System.out.println("Interview updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteInterview(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Interview ID to delete:");
            String interviewId = sc.next();
            Interview interview = session.get(Interview.class, interviewId);

            if (interview != null) {
                session.delete(interview);
                tx.commit();
                System.out.println("Interview deleted successfully.");
            } else {
                System.out.println("Interview not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllInterviews(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Interview> query = session.createQuery("from Interview", Interview.class);
        List<Interview> interviews = query.getResultList();

        for (Interview interview : interviews) {
            System.out.println(interview);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getInterview(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Interview ID:");
        String interviewId = sc.next();
        Interview interview = session.get(Interview.class, interviewId);

        if (interview != null) {
            System.out.println(interview);
        } else {
            System.out.println("Interview not found!");
        }
        session.close();
    }

    @Override
    public void getInterviewInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(interviewId) from Interview", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Interviews: " + count);
        session.close();
    }
}
