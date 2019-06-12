package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class RoleDatabaseRepository implements RoleRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRole(int id, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
