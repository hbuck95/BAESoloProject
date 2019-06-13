package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.GameModeRepository;

public class GameModeServiceImpl implements GameModeService {

	@Inject
	private GameModeRepository repo;

	@Override
	public String getAllGameModes() {
		return repo.getAllGameModes();
	}

	@Override
	public String createGameMode(String gameMode) {
		return repo.createGameMode(gameMode);
	}

	@Override
	public String deleteGameMode(int id) {
		return repo.deleteGameMode(id);
	}

	@Override
	public String updateGameMode(int id, String gameMode) {
		return repo.updateGameMode(id, gameMode);
	}

	@Override
	public String findGameMode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
