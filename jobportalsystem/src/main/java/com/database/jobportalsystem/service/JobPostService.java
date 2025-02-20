package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface JobPostService {
	void insertJobPost(SessionFactory sf);
	void updateJobPost(SessionFactory sf);
	void deleteJobPost(SessionFactory sf);
	void getAllJobPosts(SessionFactory sf);
	void getJobPost(SessionFactory sf);
	void getJobPostInformation(SessionFactory sf);

}
