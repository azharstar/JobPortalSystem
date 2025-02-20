package com.database.jobportalsystem.service;

import org.hibernate.SessionFactory;

public interface RoleService {
	void insertRole(SessionFactory sf);
	void updateRole(SessionFactory sf);
	void deleteRole(SessionFactory sf);
	void getAllRoles(SessionFactory sf);
	void getRole(SessionFactory sf);
	void getRoleInformation(SessionFactory sf);
}
