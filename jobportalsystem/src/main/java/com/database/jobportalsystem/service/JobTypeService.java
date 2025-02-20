package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface JobTypeService {
	void insertJobType(SessionFactory sf);
	void updateJobType(SessionFactory sf);
	void deleteJobType(SessionFactory sf);
	void getAllJobTypes(SessionFactory sf);
	void getJobType(SessionFactory sf);
	void getJobTypeInformation(SessionFactory sf);
}
