package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_DAMAGETYPE_SUCCESS;
import static com.bae.util.Constants.DELETE_DAMAGETYPE_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		TypedQuery<DamageType> query = entityManager.createQuery("SELECT dt FROM DamageType dt", DamageType.class);
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
		DamageType damageType = entityManager.find(DamageType.class, id);
		entityManager.remove(damageType);
		return DELETE_DAMAGETYPE_SUCCESS;
	}

	@Override
	public String updateDamageType(int id, String damageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDamageType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
