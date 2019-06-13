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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteChampion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateChampion(int id, String champion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findChampion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
