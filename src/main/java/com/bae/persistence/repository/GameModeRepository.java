package com.bae.persistence.repository;

public interface GameModeRepository {
	String getAllGameModes();

	String createGameMode(String gameMode);

	String deleteGameMode(int id);

	String updateGameMode(int id, String gameMode);

	String findGameMode(int id);

}
