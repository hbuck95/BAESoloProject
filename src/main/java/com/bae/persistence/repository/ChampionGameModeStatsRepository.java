package com.bae.persistence.repository;

public interface ChampionGameModeStatsRepository {
	String getAllChampionGameModeStats();

	String createChampionGameModeStats(String gameMode);

	String deleteChampionGameModeStats(int id);

	String updateChampionGameModeStats(int id, String gameMode);

	String findChampionGameModeStats(int gameModeId);

	String findChampionGameModeStats(String championName);

	String findChampionGameModeStats(String championName, int gameModeId);

}
