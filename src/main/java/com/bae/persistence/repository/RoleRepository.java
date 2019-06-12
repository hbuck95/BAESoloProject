package com.bae.persistence.repository;

public interface RoleRepository {

	String getAllRoles();

	String createRole(String role);

	String deleteRole(int id);

	String updateRole(int id, String role);

	String findRole(int id);
}
