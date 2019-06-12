package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Role;
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
		TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
		Collection<Role> roles = query.getResultList();
		return util.getJSONForObject(roles);
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
