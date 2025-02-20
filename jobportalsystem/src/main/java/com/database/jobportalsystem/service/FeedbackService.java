package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface FeedbackService {
	void insertFeedback(SessionFactory sf);
	void updateFeedback(SessionFactory sf);
	void deleteFeedback(SessionFactory sf);
	void getAllFeedbacks(SessionFactory sf);
	void getFeedback(SessionFactory sf);
	void getFeedbackInformation(SessionFactory sf);

}
