package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.Resume;
import com.database.jobportalsystem.entities.JobSeeker;
import com.database.jobportalsystem.service.ResumeService;

public class ResumeServiceImpl implements ResumeService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertResume(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Resume resume = new Resume();

        try {
            System.out.println("Enter Resume ID:");
            resume.setResumeId(sc.next());

            System.out.println("Enter Job Seeker ID:");
            String jobSeekerId = sc.next();
            JobSeeker jobSeeker = session.get(JobSeeker.class, jobSeekerId);

            if (jobSeeker == null) {
                System.out.println("Job Seeker not found!");
                return;
            }
            resume.setJobSeeker(jobSeeker);

            System.out.println("Enter File Path of the Resume:");
            resume.setFilePath(sc.next());

            resume.setCreatedAt(new Date());
            resume.setUpdatedAt(new Date());

            session.persist(resume);
            tx.commit();
            System.out.println("Resume added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateResume(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Resume ID to update:");
            String resumeId = sc.next();
            Resume resume = session.get(Resume.class, resumeId);

            if (resume == null) {
                System.out.println("Resume not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update File Path\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New File Path:");
                        resume.setFilePath(sc.next());
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

                resume.setUpdatedAt(new Date()); // Update timestamp
                session.saveOrUpdate(resume);
                tx.commit();
                System.out.println("Resume updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteResume(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Resume ID to delete:");
            String resumeId = sc.next();
            Resume resume = session.get(Resume.class, resumeId);

            if (resume != null) {
                session.delete(resume);
                tx.commit();
                System.out.println("Resume deleted successfully.");
            } else {
                System.out.println("Resume not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllResumes(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Resume> query = session.createQuery("from Resume", Resume.class);
        List<Resume> resumes = query.getResultList();

        for (Resume resume : resumes) {
            System.out.println(resume);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getResume(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Resume ID:");
        String resumeId = sc.next();
        Resume resume = session.get(Resume.class, resumeId);

        if (resume != null) {
            System.out.println(resume);
        } else {
            System.out.println("Resume not found!");
        }
        session.close();
    }

    @Override
    public void getResumeInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(resumeId) from Resume", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Resumes: " + count);
        session.close();
    }
}
