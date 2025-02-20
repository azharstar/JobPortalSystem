package com.database.jobportalsystem.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils{
	private static SessionFactory sessionfactory;
	
	static
	{
		try
		{
			/*--- Creating object of Configuration Class ----*/
			Configuration config = new Configuration();
			/*--- settting the properties related to database connection ------*/
			/*---setting name of driver class ----*/
			config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			/*--- setting url of database ----*/
			config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jobportalsystem");
			/*---- setting username of database ------*/
			config.setProperty("hibernate.connection.username","root");
			/*---- setting password for above user -----*/
			config.setProperty("hibernate.connection.password","123456");
			/*---- setting properties to configure logging for SQL statements*/
			/*--- property to show sql ----*/
			config.setProperty("hibernate.show_sql", true);
			/*--- property to show sql well formatted ---*/
			config.setProperty("hibernate.format_sql",true);
			/*---- setting property for schema generation ----*/
			config.setProperty("hibernate.hbm2ddl.auto","update");
			/*--------------------------------------------------------------------------------*/
			/*---- setting annotated entity class ----*/
			config.addAnnotatedClass(com.database.jobportalsystem.entities.JobSeeker.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Employer.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Application.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Category.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Feedback.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Interview.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.JobPost.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.JobType.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Resume.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Role.class);
			config.addAnnotatedClass(com.database.jobportalsystem.entities.Skill.class);
			/*---------------------------------------------------------------------------------------------------*/
			/*---- creating refernce of StandardRegistry to apply the settings -----*/
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			/*-------------------------------------------------------------------------*/
			/*----- Initializing session factory ------*/
			sessionfactory = config.buildSessionFactory(serviceRegistry);
		}
		catch(Throwable tw)
		{
			System.err.println("Unable to create session factory : "+tw);
			throw new ExceptionInInitializerError(tw);
		}
	}
	/*---- method to return sessionfactory ------*/
	public static SessionFactory getSessionFactory()
	{
		return sessionfactory;
	}
}


