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
		return repo.deleteChampionGameModeStats(id);
	}

	@Override
	public String updateChampionGameModeStats(int id, String gameMode) {
		return repo.updateChampionGameModeStats(id, gameMode);
	}

	@Override
	public String findChampionGameModeStats(int gameModeId) {
		return repo.findChampionGameModeStats(gameModeId);
	}

	@Override
	public String findChampionGameModeStats(String championName) {
		return repo.findChampionGameModeStats(championName);
	}

	@Override
	public String findChampionGameModeStats(String championName, int gameModeId) {
		return repo.findChampionGameModeStats(championName, gameModeId);
	}

}
