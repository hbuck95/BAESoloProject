package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_PANTHEON_SUCCESS;
import static com.bae.util.Constants.DELETE_PANTHEON_SUCCESS;
import static com.bae.util.Constants.PANTHEON_NOT_FOUND;
import static com.bae.util.Constants.UPDATE_PANTHEON_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Pantheon;
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
		Query query = entityManager.createQuery("SELECT p FROM Pantheon p");
		Collection<Pantheon> pantheons = query.getResultList();
		return util.getJSONForObject(pantheons);
	}

	@Override
	@Transactional(REQUIRED)
	public String createPantheon(String pantheon) {
		Pantheon newPantheon = util.getObjectForJSON(pantheon, Pantheon.class);
		entityManager.persist(newPantheon);
		return CREATE_PANTHEON_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deletePantheon(int id) {
		if (!checkPantheonExists(id)) {
			return PANTHEON_NOT_FOUND;
		}

		Pantheon pantheon = entityManager.find(Pantheon.class, id);
		entityManager.remove(pantheon);
		return DELETE_PANTHEON_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updatePantheon(int id, String pantheon) {

		if (!checkPantheonExists(id)) {
			return PANTHEON_NOT_FOUND;
		}

		Pantheon updatedPantheon = util.getObjectForJSON(pantheon, Pantheon.class);
		Pantheon oldPantheon = entityManager.find(Pantheon.class, id);

		oldPantheon.setName(updatedPantheon.getName());

		entityManager.merge(oldPantheon);
		return UPDATE_PANTHEON_SUCCESS;
	}

	@Override
	public String findPantheon(int id) {
		if (!checkPantheonExists(id)) {
			return PANTHEON_NOT_FOUND;
		}

		return util.getJSONForObject((Pantheon) entityManager.find(Pantheon.class, id));
	}

	public boolean checkPantheonExists(int id) {
		return entityManager.find(Pantheon.class, id) != null;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
