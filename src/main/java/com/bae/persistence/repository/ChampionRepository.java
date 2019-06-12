package com.bae.persistence.repository;

public interface ChampionRepository {

	String getAllChampions();

	String createChampion(String champion);

	String deleteChampion(int id);

	String updateChampion(int id, String champion);

	String findChampion(int id);

}
