package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.Role;
import com.database.jobportalsystem.service.RoleService;

public class RoleServiceImpl implements RoleService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertRole(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Role role = new Role();

        try {
            System.out.println("Enter Role ID:");
            role.setRoleId(sc.next());

            System.out.println("Enter Role Name (e.g., Admin, Job Seeker, Employer):");
            role.setName(sc.next());

            role.setCreatedAt(new Date());
            role.setUpdatedAt(new Date());

            session.persist(role);
            tx.commit();
            System.out.println("Role added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateRole(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Role ID to update:");
            String roleId = sc.next();
            Role role = session.get(Role.class, roleId);

            if (role == null) {
                System.out.println("Role not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Role Name\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Role Name:");
                        role.setName(sc.next());
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

                role.setUpdatedAt(new Date()); // Update timestamp
                session.saveOrUpdate(role);
                tx.commit();
                System.out.println("Role updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteRole(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Role ID to delete:");
            String roleId = sc.next();
            Role role = session.get(Role.class, roleId);

            if (role != null) {
                session.delete(role);
                tx.commit();
                System.out.println("Role deleted successfully.");
            } else {
                System.out.println("Role not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllRoles(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Role> query = session.createQuery("from Role", Role.class);
        List<Role> roles = query.getResultList();

        for (Role role : roles) {
            System.out.println(role);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getRole(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Role ID:");
        String roleId = sc.next();
        Role role = session.get(Role.class, roleId);

        if (role != null) {
            System.out.println(role);
        } else {
            System.out.println("Role not found!");
        }
        session.close();
    }

    @Override
    public void getRoleInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(roleId) from Role", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Roles: " + count);
        session.close();
    }
}
