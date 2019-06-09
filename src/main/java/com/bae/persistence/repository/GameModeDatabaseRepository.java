package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.bae.persistence.domain.GameMode;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class GameModeDatabaseRepository implements GameModeRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllGameModes() {
		TypedQuery<GameMode> query = entityManager.createQuery("SELECT g FROM GameMode g", GameMode.class);
		Collection<GameMode> gameModes = query.getResultList();
		return util.getJSONForObject(gameModes);
	}

	@Override
	public String createGameMode(String gameMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteGameMode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateGameMode(int id, String gameMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findGameMode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkGameModeExists(int id) {
		// Execute a query rather than using entityManager.find/.contains to improve
		// performance by not having to retrieve records from the database.
		long results = (long) entityManager.createQuery("SELECT COUNT(g) FROM GameMode g WHERE c.gamemode_id = id")
				.getSingleResult();

		return results == 1;
	}

}
