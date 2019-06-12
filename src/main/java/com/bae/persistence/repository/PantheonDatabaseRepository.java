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
import javax.persistence.TypedQuery;
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
		TypedQuery<Pantheon> query = entityManager.createQuery("SELECT p FROM Pantheon p", Pantheon.class);
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
		Pantheon pantheon = entityManager.find(Pantheon.class, id);
		entityManager.remove(pantheon);
		return DELETE_PANTHEON_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updatePantheon(int id, String pantheon) {
		Pantheon updatedMode = util.getObjectForJSON(pantheon, Pantheon.class);

		if (!checkPantheonExists(id)) {
			return PANTHEON_NOT_FOUND;
		}

		entityManager.merge(updatedMode);
		return UPDATE_PANTHEON_SUCCESS;
	}

	@Override
	public String findPantheon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkPantheonExists(int id) {
		// Execute a query rather than using entityManager.find/.contains to improve
		// performance by not having to retrieve records from the database.
		return (long) entityManager
				.createQuery(String.format("SELECT COUNT(p) FROM Pantheon p WHERE p.pantheon_id = '%s'", id))
				.getSingleResult() == 1;

	}

}
