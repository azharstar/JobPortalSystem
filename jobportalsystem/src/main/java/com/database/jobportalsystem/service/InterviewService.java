package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface InterviewService {
	void insertInterview(SessionFactory sf);
	void updateInterview(SessionFactory sf);
	void deleteInterview(SessionFactory sf);
	void getAllInterviews(SessionFactory sf);
	void getInterview(SessionFactory sf);
	void getInterviewInformation(SessionFactory sf);
}
