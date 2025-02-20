package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.database.jobportalsystem.entities.Employer;
import com.database.jobportalsystem.entities.JobPost;
import com.database.jobportalsystem.entities.Feedback;
import com.database.jobportalsystem.entities.Role;
import com.database.jobportalsystem.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertEmployee(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Employer employer = new Employer();

        try {
            System.out.println("Welcome to Employer Registration");

            System.out.println("Enter Employer ID:");
            employer.setEmployerId(sc.next());

            System.out.println("Enter Company Name:");
            employer.setCompanyName(sc.next());

            System.out.println("Enter Email:");
            employer.setEmail(sc.next());

            System.out.println("Enter Password:");
            employer.setPassword(sc.next());

            System.out.println("Enter Phone Number:");
            String phone = getValidIntInput("Enter a valid phone number:");
            employer.setPhone(phone);

            System.out.println("Enter Address:");
            employer.setAddress(sc.next());

            employer.setCreatedAt(new Date());
            employer.setUpdatedAt(new Date());

            session.persist(employer);
            tx.commit();
            System.out.println("Employer inserted successfully!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateEmployee(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Employer ID to update:");
            String employerId = sc.next();
            Employer employer = session.get(Employer.class, employerId);

            if (employer == null) {
                System.out.println("Employer not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Company Name\n2. Update Email\n3. Update Password\n4. Update Phone\n5. Update Address\n6. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Company Name:");
                        employer.setCompanyName(sc.next());
                        break;
                    case 2:
                        System.out.println("Enter New Email:");
                        employer.setEmail(sc.next());
                        break;
                    case 3:
                        System.out.println("Enter New Password:");
                        employer.setPassword(sc.next());
                        break;
                    case 4:
                        System.out.println("Enter New Phone:");
                        employer.setPhone(getValidIntInput("Enter a valid phone number:"));
                        break;
                    case 5:
                        System.out.println("Enter New Address:");
                        employer.setAddress(sc.next());
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

                employer.setUpdatedAt(new Date());
                session.saveOrUpdate(employer);
                tx.commit();
                System.out.println("Employer updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteEmployee(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Employer ID:");
            String employerId = sc.next();
            Employer employer = session.get(Employer.class, employerId);

            if (employer != null) {
                session.delete(employer);
                tx.commit();
                System.out.println("Employer deleted successfully");
            } else {
                System.out.println("Employer not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllEmployees(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Employer> query = session.createQuery("from Employer", Employer.class);
        List<Employer> employers = query.getResultList();

        for (Employer emp : employers) {
            System.out.println(emp);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getEmployee(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Employer ID:");
        String employerId = sc.next();
        Employer employer = session.get(Employer.class, employerId);

        if (employer != null) {
            System.out.println(employer);
        } else {
            System.out.println("Employer not found!");
        }
        session.close();
    }

    @Override
    public void getEmployeeInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(employerId) from Employer", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Employees: " + count);
        session.close();
    }

    // Helper method to get valid integer input
    private String getValidIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }
}

