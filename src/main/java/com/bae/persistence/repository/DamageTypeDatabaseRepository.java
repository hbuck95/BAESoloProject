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
public class DamageTypeDatabaseRepository implements DamageTypeRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllDamageTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createDamageType(String damageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDamageType(int id) {
		// TODO Auto-generated method stub
		return null;
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
