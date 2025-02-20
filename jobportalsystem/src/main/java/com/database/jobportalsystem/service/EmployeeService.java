package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface EmployeeService {
	void insertEmployee(SessionFactory sf);
	void updateEmployee(SessionFactory sf);
	void deleteEmployee(SessionFactory sf);
	void getAllEmployees(SessionFactory sf);
	void getEmployee(SessionFactory sf);
	void getEmployeeInformation(SessionFactory sf);

}
