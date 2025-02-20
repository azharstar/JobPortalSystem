package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface ResumeService {
	void insertResume(SessionFactory sf);
	void updateResume(SessionFactory sf);
	void deleteResume(SessionFactory sf);
	void getAllResumes(SessionFactory sf);
	void getResume(SessionFactory sf);
	void getResumeInformation(SessionFactory sf);
}
