package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.ChampionGameModeStatsRepository;

public class ChampionGameModeStatsServiceImpl implements ChampionGameModeStatsService {

	@Inject
	private ChampionGameModeStatsRepository repo;

	@Override
	public String getAllChampionGameModeStats() {
		return repo.getAllChampionGameModeStats();
	}

	@Override
	public String createChampionGameModeStats(String gameMode) {
		return repo.createChampionGameModeStats(gameMode);
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
