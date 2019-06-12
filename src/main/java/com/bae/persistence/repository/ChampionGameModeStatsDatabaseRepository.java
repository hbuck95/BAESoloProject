package com.bae.persistence.repository;

import static com.bae.util.Constants.CREATE_STATS_SUCCESS;
import static com.bae.util.Constants.DELETE_STATS_SUCCESS;
import static com.bae.util.Constants.STATS_NOT_FOUND;
import static com.bae.util.Constants.UPDATE_STATS_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.bae.persistence.domain.ChampionGameModeStats;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class ChampionGameModeStatsDatabaseRepository implements ChampionGameModeStatsRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllChampionGameModeStats() {
		TypedQuery<ChampionGameModeStats> query = entityManager.createQuery("SELECT gms FROM ChampionGameModeStats gms",
				ChampionGameModeStats.class);
		Collection<ChampionGameModeStats> championGameModeStats = query.getResultList();
		return util.getJSONForObject(championGameModeStats);
	}

	@Override
	@Transactional(REQUIRED)
	public String createChampionGameModeStats(String championGameModeStats) {
		ChampionGameModeStats newStats = util.getObjectForJSON(championGameModeStats, ChampionGameModeStats.class);
		entityManager.persist(newStats);
		return CREATE_STATS_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteChampionGameModeStats(int id) {
		ChampionGameModeStats stats = entityManager.find(ChampionGameModeStats.class, id);
		entityManager.remove(stats);
		return DELETE_STATS_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateChampionGameModeStats(int id, String championGameModeStats) {
		ChampionGameModeStats updatedMode = util.getObjectForJSON(championGameModeStats, ChampionGameModeStats.class);

		if (!checkGameModeStatsExist(id)) {
			return STATS_NOT_FOUND;
		}

		entityManager.merge(updatedMode);
		return UPDATE_STATS_SUCCESS;
	}

	@Override
	public String findChampionGameModeStats(int id) {
		if (!checkGameModeStatsExist(id)) {
			return STATS_NOT_FOUND;
		}

		return util.getJSONForObject((ChampionGameModeStats) entityManager.find(ChampionGameModeStats.class, id));
	}

	@Override
	public String findChampionGameModeStats(String championName) {
		String queryString = String.format(
				"SELECT cgms FROM CHAMPIONGAMEMODESTATS cgms WHERE cgms.CHAMPION_ID  in (SELECT c.CHAMPION_ID from CHAMPION c WHERE c.CHAMPION_NAME = '%s')",
				championName);

		TypedQuery<ChampionGameModeStats> query = entityManager.createQuery(queryString, ChampionGameModeStats.class);
		Collection<ChampionGameModeStats> championGameModeStats = query.getResultList();
		return util.getJSONForObject((ChampionGameModeStats) championGameModeStats);
	}

	@Override
	public String findChampionGameModeStats(String championName, int gameModeId) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkGameModeStatsExist(int id) {
		// Execute a query rather than using entityManager.find/.contains to improve
		// performance by not having to retrieve records from the database.

		return (long) entityManager.createQuery(String.format(
				"SELECT COUNT(cgms) FROM ChampionGameModeStats cgms WHERE cgms.championgamemodestats_id = '%s'", id))
				.getSingleResult() == 1;

	}

}
