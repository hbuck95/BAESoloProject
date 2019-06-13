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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteGameMode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateGameMode(int id, String gameMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findGameMode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
