package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

	@Inject
	private RoleRepository repo;

	@Override
	public String getAllRoles() {
		return repo.getAllRoles();
	}

	@Override
	public String createRole(String role) {
		return repo.createRole(role);
	}

	@Override
	public String deleteRole(int id) {
		return repo.deleteRole(id);
	}

	@Override
	public String updateRole(int id, String role) {
		return repo.updateRole(id, role);
	}

	@Override
	public String findRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
