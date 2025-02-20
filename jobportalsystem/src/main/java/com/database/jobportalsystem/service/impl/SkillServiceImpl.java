package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.database.jobportalsystem.entities.Skill;
import com.database.jobportalsystem.service.SkillService;

public class SkillServiceImpl implements SkillService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertSkill(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Skill skill = new Skill();

        try {
            System.out.println("Enter Skill ID:");
            skill.setSkillId(sc.next());

            System.out.println("Enter Skill Name:");
            skill.setName(sc.next());

            skill.setCreatedAt(new Date());
            skill.setUpdatedAt(new Date());

            session.persist(skill);
            tx.commit();
            System.out.println("Skill added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateSkill(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Skill ID to update:");
            String skillId = sc.next();
            Skill skill = session.get(Skill.class, skillId);

            if (skill == null) {
                System.out.println("Skill not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Skill Name\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Skill Name:");
                        skill.setName(sc.next());
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

                skill.setUpdatedAt(new Date()); // Update timestamp
                session.saveOrUpdate(skill);
                tx.commit();
                System.out.println("Skill updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSkill(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Skill ID to delete:");
            String skillId = sc.next();
            Skill skill = session.get(Skill.class, skillId);

            if (skill != null) {
                session.delete(skill);
                tx.commit();
                System.out.println("Skill deleted successfully.");
            } else {
                System.out.println("Skill not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllSkills(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Skill> query = session.createQuery("from Skill", Skill.class);
        List<Skill> skills = query.getResultList();

        for (Skill skill : skills) {
            System.out.println(skill);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getSkill(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Skill ID:");
        String skillId = sc.next();
        Skill skill = session.get(Skill.class, skillId);

        if (skill != null) {
            System.out.println(skill);
        } else {
            System.out.println("Skill not found!");
        }
        session.close();
    }

    @Override
    public void getSkillInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(skillId) from Skill", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Skills: " + count);
        session.close();
    }
}
