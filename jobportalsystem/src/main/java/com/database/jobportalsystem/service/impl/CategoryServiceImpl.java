package com.database.jobportalsystem.service.impl;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.database.jobportalsystem.entities.Category;
import com.database.jobportalsystem.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertCategory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Category category = new Category();

        try {
            System.out.println("Enter Category ID:");
            category.setCategoryId(sc.next());

            System.out.println("Enter Category Name:");
            sc.nextLine(); // Consume the newline
            category.setName(sc.nextLine());

            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());

            session.persist(category);
            tx.commit();
            System.out.println("Category added successfully!");
        } finally {
            session.close();
        }
    }

    @Override
    public void updateCategory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Category ID to update:");
            String categoryId = sc.next();
            Category category = session.get(Category.class, categoryId);

            if (category == null) {
                System.out.println("Category not found!");
                return;
            }

            while (true) {
                System.out.println("Choose an Option for Update:\n" +
                        "1. Update Name\n2. Exit");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter New Name:");
                        sc.nextLine(); // Consume the newline
                        category.setName(sc.nextLine());
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

                category.setUpdatedAt(new Date());
                session.saveOrUpdate(category);
                tx.commit();
                System.out.println("Category updated successfully!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteCategory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.println("Enter Category ID to delete:");
            String categoryId = sc.next();
            Category category = session.get(Category.class, categoryId);

            if (category != null) {
                session.delete(category);
                tx.commit();
                System.out.println("Category deleted successfully.");
            } else {
                System.out.println("Category not found!");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void getAllCategories(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query<Category> query = session.createQuery("from Category", Category.class);
        List<Category> categories = query.getResultList();

        for (Category category : categories) {
            System.out.println(category);
        }
        tx.commit();
        session.close();
    }

    @Override
    public void getCategory(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter Category ID:");
        String categoryId = sc.next();
        Category category = session.get(Category.class, categoryId);

        if (category != null) {
            System.out.println(category);
        } else {
            System.out.println("Category not found!");
        }
        session.close();
    }

    @Override
    public void getCategoryInformation(SessionFactory sf) {
        session = sf.openSession();

        Query<Long> query = session.createQuery("select count(categoryId) from Category", Long.class);
        Long count = query.uniqueResult();

        System.out.println("Total number of Categories: " + count);
        session.close();
    }
}
