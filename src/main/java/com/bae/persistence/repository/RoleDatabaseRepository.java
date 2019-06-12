package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_ROLE_SUCCESS;
import static com.bae.util.Constants.DELETE_ROLE_SUCCESS;
import static com.bae.util.Constants.ROLE_NOT_FOUND;
import static com.bae.util.Constants.UPDATE_ROLE_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
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
	@Transactional(REQUIRED)
	public String createRole(String role) {
		Role newRole = util.getObjectForJSON(role, Role.class);
		entityManager.persist(newRole);
		return CREATE_ROLE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteRole(int id) {
		Role role = entityManager.find(Role.class, id);
		entityManager.remove(role);
		return DELETE_ROLE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateRole(int id, String role) {
		Role updatedRole = util.getObjectForJSON(role, Role.class);

		if (!checkGameModeExists(id)) {
			return ROLE_NOT_FOUND;
		}

		entityManager.merge(updatedRole);
		return UPDATE_ROLE_SUCCESS;
	}

	@Override
	public String findRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkGameModeExists(int id) {
		// Execute a query rather than using entityManager.find/.contains to improve
		// performance by not having to retrieve records from the database.

		return (long) entityManager
				.createQuery(String.format("SELECT COUNT(g) FROM GameMode g WHERE g.gamemode_id = '%s'", id))
				.getSingleResult() == 1;

	}

}
