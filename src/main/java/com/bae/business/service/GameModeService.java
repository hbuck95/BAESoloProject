package com.bae.business.service;

public interface GameModeService {
	String getAllGameModes();

	String createGameMode(String gameMode);

	String deleteGameMode(int id);

	String updateGameMode(int id, String gameMode);

	String findGameMode(int id);
}
