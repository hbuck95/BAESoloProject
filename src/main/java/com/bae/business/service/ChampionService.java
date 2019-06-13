package com.bae.business.service;

public interface ChampionService {
	String getAllChampions();

	String createChampion(String champion);

	String deleteChampion(int id);

	String updateChampion(int id, String champion);

	String findChampion(int id);
}
