package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface ApplicationService {
	void insertApplication(SessionFactory sf);
	void updateApplication(SessionFactory sf);
	void deleteApplication(SessionFactory sf);
	void getAllApplications(SessionFactory sf);
	void getApplication(SessionFactory sf);
	void getApplicationInformation(SessionFactory sf);
}
