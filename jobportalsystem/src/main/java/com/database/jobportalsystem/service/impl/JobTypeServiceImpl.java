package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.JobType;
import com.database.jobportalsystem.entities.JobPost;
import com.database.jobportalsystem.service.JobTypeService;

public class JobTypeServiceImpl implements JobTypeService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertJobType(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        JobType jobType = new JobType();

        try {
            System.out.println("Enter Job Type ID:");
            jobType.setJobTypeId(sc.next());

            System.out.println("Enter Job Type Name:");
            jobType.setName(sc.next());

            jobType.setCreatedAt(new Date());
            jobType.setUpdatedAt(new Date());

            session.persist(jobType);
            tx.commit();
            System.out.println("Job Type added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateJobType(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Job Type ID to update:");
            String jobTypeId = sc.next();
            JobType jobType = session.get(JobType.class, jobTypeId);

            if (jobType == null) {
                System.out.println("Job Type not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Job Type Name\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Job Type Name:");
                        jobType.setName(sc.next());
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

                jobType.setUpdatedAt(new Date()); // Update timestamp
                session.saveOrUpdate(jobType);
                tx.commit();
                System.out.println("Job Type updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteJobType(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Job Type ID to delete:");
            String jobTypeId = sc.next();
            JobType jobType = session.get(JobType.class, jobTypeId);

            if (jobType != null) {
                session.delete(jobType);
                tx.commit();
                System.out.println("Job Type deleted successfully.");
            } else {
                System.out.println("Job Type not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllJobTypes(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<JobType> query = session.createQuery("from JobType", JobType.class);
        List<JobType> jobTypes = query.getResultList();

        for (JobType jobType : jobTypes) {
            System.out.println(jobType);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getJobType(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Job Type ID:");
        String jobTypeId = sc.next();
        JobType jobType = session.get(JobType.class, jobTypeId);

        if (jobType != null) {
            System.out.println(jobType);
        } else {
            System.out.println("Job Type not found!");
        }
        session.close();
    }

    @Override
    public void getJobTypeInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(jobTypeId) from JobType", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Job Types: " + count);
        session.close();
    }
}
