package com.bae.business.service;

public interface ChampionGameModeStatsService {
	String getAllChampionGameModeStats();

	String createChampionGameModeStats(String gameMode);

	String deleteChampionGameModeStats(int id);

	String updateChampionGameModeStats(int id, String gameMode);

	String findChampionGameModeStats(int id);
}
