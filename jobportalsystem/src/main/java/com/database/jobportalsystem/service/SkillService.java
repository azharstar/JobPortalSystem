package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface SkillService {
	void insertSkill(SessionFactory sf);
	void updateSkill(SessionFactory sf);
	void deleteSkill(SessionFactory sf);
	void getAllSkills(SessionFactory sf);
	void getSkill(SessionFactory sf);
	void getSkillInformation(SessionFactory sf);
}
