package com.bae.persistence.repository;

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
	public String createChampionGameModeStats(String gameMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteChampionGameModeStats(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateChampionGameModeStats(int id, String gameMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findChampionGameModeStats(int gameModeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findChampionGameModeStats(String championName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findChampionGameModeStats(String championName, int gameModeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
