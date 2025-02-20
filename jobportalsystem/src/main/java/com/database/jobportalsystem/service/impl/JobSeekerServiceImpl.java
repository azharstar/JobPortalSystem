package com.database.jobportalsystem.service.impl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.JobSeeker;
import com.database.jobportalsystem.entities.Skill;
import com.database.jobportalsystem.service.JobSeekerService;

public class JobSeekerServiceImpl implements JobSeekerService {

    @Override
    public void insertJobSeeker(SessionFactory sf) {
        try (Scanner sc = new Scanner(System.in); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            JobSeeker jobSeeker = new JobSeeker();

            System.out.println("Welcome to Job Seeker Registration");

            System.out.print("Enter JobSeeker ID: ");
            jobSeeker.setJobSeekerId(sc.next());

            System.out.print("Enter Job Seeker's Name: ");
            sc.nextLine(); // Consume newline
            jobSeeker.setName(sc.nextLine());

            System.out.print("Enter Job Seeker's Email: ");
            jobSeeker.setEmail(sc.next());

            System.out.print("Enter Job Seeker's Password: ");
            jobSeeker.setPassword(sc.next());

            jobSeeker.setCreatedAt(new Date());
            jobSeeker.setUpdatedAt(new Date());

            // Handling Skills (Many-to-Many)
            Set<Skill> skills = new HashSet<>();
            System.out.print("Enter the number of skills: ");
            int skillCount = sc.nextInt();

            for (int i = 0; i < skillCount; i++) {
                System.out.print("Enter Skill ID: ");
                int skillId = sc.nextInt();
                Skill skill = session.get(Skill.class, skillId);
                if (skill != null) {
                    skills.add(skill);
                } else {
                    System.out.println("Skill ID " + skillId + " not found.");
                }
            }
            jobSeeker.setSkills(skills);

            // Save job seeker
            session.persist(jobSeeker);
            tx.commit();
            System.out.println("Job Seeker inserted successfully!");
        }
    }

    @Override
    public void updateJobSeeker(SessionFactory sf) {
        try (Scanner sc = new Scanner(System.in); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            System.out.print("Enter JobSeeker ID to update: ");
            String jobSeekerId = sc.next();
            JobSeeker jobSeeker = session.get(JobSeeker.class, jobSeekerId);

            if (jobSeeker == null) {
                System.out.println("Job Seeker not found!");
                return;
            }

            boolean updated = false;
            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Name\n2. Update Email\n3. Update Password\n4. Exit");
                int option = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        System.out.print("Enter New Name: ");
                        jobSeeker.setName(sc.nextLine());
                        updated = true;
                        break;
                    case 2:
                        System.out.print("Enter New Email: ");
                        jobSeeker.setEmail(sc.next());
                        updated = true;
                        break;
                    case 3:
                        System.out.print("Enter New Password: ");
                        jobSeeker.setPassword(sc.next());
                        updated = true;
                        break;
                    case 4:
                        if (updated) {
                            jobSeeker.setUpdatedAt(new Date());
                            session.update(jobSeeker);
                            tx.commit();
                            System.out.println("Job Seeker updated successfully!");
                        }
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        }
    }

    @Override
    public void deleteJobSeeker(SessionFactory sf) {
        try (Scanner sc = new Scanner(System.in); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            System.out.print("Enter JobSeeker ID: ");
            String jobSeekerId = sc.next();
            JobSeeker jobSeeker = session.get(JobSeeker.class, jobSeekerId);

            if (jobSeeker != null) {
                session.delete(jobSeeker);
                tx.commit();
                System.out.println("Job Seeker deleted successfully.");
            } else {
                System.out.println("Job Seeker not found!");
            }
        }
    }

    @Override
    public void getAllJobSeekers(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            List<JobSeeker> jobSeekers = session.createQuery("from JobSeeker", JobSeeker.class).getResultList();
            if (jobSeekers.isEmpty()) {
                System.out.println("No Job Seekers found.");
            } else {
                jobSeekers.forEach(System.out::println);
            }
        }
    }

    @Override
    public void getJobSeeker(SessionFactory sf) {
        try (Scanner sc = new Scanner(System.in); Session session = sf.openSession()) {
            System.out.print("Enter JobSeeker ID: ");
            String jobSeekerId = sc.next();
            JobSeeker jobSeeker = session.get(JobSeeker.class, jobSeekerId);

            if (jobSeeker != null) {
                System.out.println(jobSeeker);
            } else {
                System.out.println("Job Seeker not found!");
            }
        }
    }

    @Override
    public void getJobSeekerInformation(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Long count = session.createQuery("select count(jobSeekerId) from JobSeeker", Long.class).uniqueResult();
            System.out.println("Total number of Job Seekers: " + count);
        }
    }
}
