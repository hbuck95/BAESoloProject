package com.bae.business.service;

public interface RoleService {
	String getAllRoles();

	String createRole(String role);

	String deleteRole(int id);

	String updateRole(int id, String role);

	String findRole(int id);

}
