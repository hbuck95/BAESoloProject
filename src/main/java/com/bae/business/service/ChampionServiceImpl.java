package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.ChampionRepository;

public class ChampionServiceImpl implements ChampionService {

	@Inject
	private ChampionRepository repo;

	@Override
	public String getAllChampions() {
		return repo.getAllChampions();
	}

	@Override
	public String createChampion(String champion) {
		return repo.createChampion(champion);
	}

	@Override
	public String deleteChampion(int id) {
		return repo.deleteChampion(id);
	}

	@Override
	public String updateChampion(int id, String champion) {
		return repo.updateChampion(id, champion);
	}

	@Override
	public String findChampion(int id) {
		return repo.findChampion(id);
	}

}
