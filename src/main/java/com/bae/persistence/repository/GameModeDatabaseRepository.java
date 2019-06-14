package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_GAMEMODE_SUCCESS;
import static com.bae.util.Constants.DELETE_GAMEMODE_SUCCESS;
import static com.bae.util.Constants.GAMEMODE_NOT_FOUND;
import static com.bae.util.Constants.UPDATE_GAMEMODE_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
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
	@Transactional(REQUIRED)
	public String createGameMode(String gameMode) {
		GameMode mode = util.getObjectForJSON(gameMode, GameMode.class);
		entityManager.persist(mode);
		return CREATE_GAMEMODE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteGameMode(int id) {
		GameMode mode = entityManager.find(GameMode.class, id);
		entityManager.remove(mode);
		return DELETE_GAMEMODE_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateGameMode(int id, String gameMode) {
		if (!checkGameModeExists(id)) {
			return GAMEMODE_NOT_FOUND;
		}

		GameMode updatedMode = util.getObjectForJSON(gameMode, GameMode.class);
		GameMode oldMode = entityManager.find(GameMode.class, id);

		oldMode.setName(updatedMode.getName());

		entityManager.merge(oldMode);
		return UPDATE_GAMEMODE_SUCCESS;
	}

	@Override
	public String findGameMode(int id) {
		if (!checkGameModeExists(id)) {
			return GAMEMODE_NOT_FOUND;
		}

		return util.getJSONForObject((GameMode) entityManager.find(GameMode.class, id));
	}

	private boolean checkGameModeExists(int id) {
		return entityManager.find(GameMode.class, id) != null;
	}

}
