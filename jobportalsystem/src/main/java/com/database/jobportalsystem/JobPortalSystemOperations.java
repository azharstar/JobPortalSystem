package com.database.jobportalsystem;

import java.util.Scanner;
import org.hibernate.SessionFactory;
import com.database.jobportalsystem.service.impl.*;
import com.database.jobportalsystem.utils.HibernateUtils;

public class JobPortalSystemOperations {
    
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        
        try {
            while (isRunning) {
                System.out.println("---- Welcome to Job Portal System ----");
                System.out.println("Select an option:\n1. Job Seeker\n2. Employer\n3. Job Post\n4. Application\n5. Interview\n6. Category\n7. Skill\n8. Feedback\n9. Job Type\n10. Role\n11. Resume\n12. Exit");
                System.out.print("Enter your choice: ");
                
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Consume invalid input
                    continue;
                }
                
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1: manageJobSeeker(sc, factory); break;
                    case 2: manageEmployer(sc, factory); break;
                    case 3: manageJobPost(sc, factory); break;
                    case 4: manageApplication(sc, factory); break;
                    case 5: manageInterview(sc, factory); break;
                    case 6: manageCategory(sc, factory); break;
                    case 7: manageSkill(sc, factory); break;
                    case 8: manageFeedback(sc, factory); break;
                    case 9: manageJobType(sc, factory); break;
                    case 10: manageRole(sc, factory); break;
                    case 11: manageResume(sc, factory); break;
                    case 12: 
                        isRunning = false;
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } finally {
            sc.close();
            factory.close();
        }
    }

    // Job Seeker Management
    private static void manageJobSeeker(Scanner sc, SessionFactory factory) {
        JobSeekerServiceImpl service = new JobSeekerServiceImpl();
        manageEntity(sc, factory, service, "Job Seeker");
    }

    // Employer Management
    private static void manageEmployer(Scanner sc, SessionFactory factory) {
        EmployeeServiceImpl service = new EmployeeServiceImpl();
        manageEntity(sc, factory, service, "Employer");
    }

    // Job Post Management
    private static void manageJobPost(Scanner sc, SessionFactory factory) {
        JobPostServiceImpl service = new JobPostServiceImpl();
        manageEntity(sc, factory, service, "Job Post");
    }

    // Application Management
    private static void manageApplication(Scanner sc, SessionFactory factory) {
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        manageEntity(sc, factory, service, "Application");
    }

    // Interview Management
    private static void manageInterview(Scanner sc, SessionFactory factory) {
        InterviewServiceImpl service = new InterviewServiceImpl();
        manageEntity(sc, factory, service, "Interview");
    }

    // Category Management
    private static void manageCategory(Scanner sc, SessionFactory factory) {
        CategoryServiceImpl service = new CategoryServiceImpl();
        manageEntity(sc, factory, service, "Category");
    }

    // Skill Management
    private static void manageSkill(Scanner sc, SessionFactory factory) {
        SkillServiceImpl service = new SkillServiceImpl();
        manageEntity(sc, factory, service, "Skill");
    }

    // Feedback Management
    private static void manageFeedback(Scanner sc, SessionFactory factory) {
        FeedbackServiceImpl service = new FeedbackServiceImpl();
        manageEntity(sc, factory, service, "Feedback");
    }

    // Job Type Management
    private static void manageJobType(Scanner sc, SessionFactory factory) {
        JobTypeServiceImpl service = new JobTypeServiceImpl();
        manageEntity(sc, factory, service, "Job Type");
    }

    // Role Management
    private static void manageRole(Scanner sc, SessionFactory factory) {
        RoleServiceImpl service = new RoleServiceImpl();
        manageEntity(sc, factory, service, "Role");
    }

    // Resume Management
    private static void manageResume(Scanner sc, SessionFactory factory) {
        ResumeServiceImpl service = new ResumeServiceImpl();
        manageEntity(sc, factory, service, "Resume");
    }

    // Generic Entity Management
    private static void manageEntity(Scanner sc, SessionFactory factory, Object service, String entityName) {
        while (true) {
            System.out.println(entityName + " Management:");
            System.out.println("1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
            System.out.print("Enter your choice: ");
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }
            
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            try {
                switch (option) {
                    case 1: service.getClass().getMethod("insert" + entityName, SessionFactory.class).invoke(service, factory); break;
                    case 2: service.getClass().getMethod("update" + entityName, SessionFactory.class).invoke(service, factory); break;
                    case 3: service.getClass().getMethod("delete" + entityName, SessionFactory.class).invoke(service, factory); break;
                    case 4: service.getClass().getMethod("getAll" + entityName + "s", SessionFactory.class).invoke(service, factory); break;
                    case 5: service.getClass().getMethod("get" + entityName, SessionFactory.class).invoke(service, factory); break;
                    case 6: service.getClass().getMethod("get" + entityName + "Information", SessionFactory.class).invoke(service, factory); break;
                    case 7: return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
