package com.bae.persistence.repository;

import static com.bae.util.Constants.CHAMPION_NOT_FOUND;
import static com.bae.util.Constants.CREATE_CHAMPION_SUCCESS;
import static com.bae.util.Constants.DELETE_CHAMPION_SUCCESS;
import static com.bae.util.Constants.UPDATE_CHAMPION_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Champion;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class ChampionDatabaseRepository implements ChampionRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllChampions() {
		TypedQuery<Champion> query = entityManager.createQuery("SELECT c FROM Champion c", Champion.class);
		Collection<Champion> accounts = query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createChampion(String champion) {
		Champion champ = util.getObjectForJSON(champion, Champion.class);
		entityManager.persist(champ);
		return CREATE_CHAMPION_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteChampion(int id) {
		Champion champ = entityManager.find(Champion.class, id);
		entityManager.remove(champ);
		return DELETE_CHAMPION_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateChampion(int id, String champion) {

		if (!checkChampionExists(id)) {
			return CHAMPION_NOT_FOUND;
		}

		// Get our updated object using the json string parameter
		Champion updatedChampion = util.getObjectForJSON(champion, Champion.class);

		// Retrieve the old champion so we can update it with the new champion values
		// Then use the entity manager to merge it into the database.
		// This updates the fields without affecting the primary key
		Champion oldChampion = entityManager.find(Champion.class, id);

		oldChampion.setName(updatedChampion.getName());
		oldChampion.setPantheon(updatedChampion.getPantheon());
		oldChampion.setDamageType(updatedChampion.getDamageType());
		oldChampion.setRole(updatedChampion.getRole());
		oldChampion.setHealth(updatedChampion.getHealth());
		oldChampion.setDamage(updatedChampion.getDamage());

		entityManager.merge(oldChampion);
		return UPDATE_CHAMPION_SUCCESS;
	}

	@Override
	public String findChampion(int id) {
		if (!checkChampionExists(id)) {
			return CHAMPION_NOT_FOUND;
		}

		return util.getJSONForObject((Champion) entityManager.find(Champion.class, id));
	}

	private boolean checkChampionExists(int id) {
		return entityManager.find(Champion.class, id) != null;
	}

}
