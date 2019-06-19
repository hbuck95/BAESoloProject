package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_DAMAGETYPE_SUCCESS;
import static com.bae.util.Constants.DAMAGETYPE_NOT_FOUND;
import static com.bae.util.Constants.DELETE_DAMAGETYPE_SUCCESS;
import static com.bae.util.Constants.UPDATE_DAMAGETYPE_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.DamageType;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class DamageTypeDatabaseRepository implements DamageTypeRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllDamageTypes() {
		Query query = entityManager.createQuery("SELECT dt FROM DamageType dt");
		Collection<DamageType> damageTypes = query.getResultList();
		return util.getJSONForObject(damageTypes);
	}

	@Override
	@Transactional(REQUIRED)
	public String createDamageType(String damageType) {
		DamageType newDamageType = util.getObjectForJSON(damageType, DamageType.class);
		entityManager.persist(newDamageType);
		return CREATE_DAMAGETYPE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteDamageType(int id) {
		if (!checkDamageTypeExists(id)) {
			return DAMAGETYPE_NOT_FOUND;
		}

		DamageType damageType = entityManager.find(DamageType.class, id);
		entityManager.remove(damageType);
		return DELETE_DAMAGETYPE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateDamageType(int id, String damageType) {
		if (!checkDamageTypeExists(id)) {
			return DAMAGETYPE_NOT_FOUND;
		}

		DamageType updatedDamageType = util.getObjectForJSON(damageType, DamageType.class);
		DamageType oldDamageType = entityManager.find(DamageType.class, id);

		oldDamageType.setName(updatedDamageType.getName());

		entityManager.merge(oldDamageType);
		return UPDATE_DAMAGETYPE_SUCCESS;
	}

	@Override
	public String findDamageType(int id) {
		if (!checkDamageTypeExists(id)) {
			return DAMAGETYPE_NOT_FOUND;
		}

		return util.getJSONForObject((DamageType) entityManager.find(DamageType.class, id));
	}

	public boolean checkDamageTypeExists(int id) {
		return entityManager.find(DamageType.class, id) != null;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
