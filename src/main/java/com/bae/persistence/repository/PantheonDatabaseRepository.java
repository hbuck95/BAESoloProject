package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class PantheonDatabaseRepository implements PantheonRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllPantheons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(REQUIRED)
	public String createPantheon(String pantheon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(REQUIRED)
	public String deletePantheon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(REQUIRED)
	public String updatePantheon(int id, String pantheon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPantheon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
