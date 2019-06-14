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
		if (!checkGameModeStatsExist(id)) {
			return STATS_NOT_FOUND;
		}

		ChampionGameModeStats stats = entityManager.find(ChampionGameModeStats.class, id);
		entityManager.remove(stats);
		return DELETE_STATS_SUCCESS;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateChampionGameModeStats(int id, String championGameModeStats) {
		if (!checkGameModeStatsExist(id)) {
			return STATS_NOT_FOUND;
		}

		ChampionGameModeStats updatedStats = util.getObjectForJSON(championGameModeStats, ChampionGameModeStats.class);
		ChampionGameModeStats oldStats = entityManager.find(ChampionGameModeStats.class, id);

		oldStats.setChampion(updatedStats.getChampion());
		oldStats.setBanRate(updatedStats.getBanRate());
		oldStats.setWinRate(updatedStats.getWinRate());
		oldStats.setPickRate(updatedStats.getPickRate());
		oldStats.setGameMode(updatedStats.getGameMode());

		entityManager.merge(oldStats);
		return UPDATE_STATS_SUCCESS;
	}

	@Override
	public String findChampionGameModeStats(int id) {
		if (!checkGameModeStatsExist(id)) {
			return STATS_NOT_FOUND;
		}

		return util.getJSONForObject((ChampionGameModeStats) entityManager.find(ChampionGameModeStats.class, id));
	}

	private boolean checkGameModeStatsExist(int id) {
		return entityManager.find(ChampionGameModeStats.class, id) != null;
	}

}
