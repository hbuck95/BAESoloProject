package com.bae.tests.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.GameMode;
import com.bae.persistence.repository.GameModeDatabaseRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class GameModeDatabaseRepositoryTest {

	@InjectMocks
	private GameModeDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, GameMode> gameModeMap;
	private static final String MOCK_GAMEMODE_ARRAY = "[{\"id\":1,\"name\":\"Conquest\"}]";
	private static final String MOCK_GAMEMODE_OBJECT = "{\"id\":1,\"name\":\"Conquest\"}";
	private GameMode gameModeB;

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		gameModeMap = new HashMap<>();
		gameModeMap.put(1, new GameMode.Builder().id(1).name("Conquest").build());
		gameModeB = new GameMode.Builder().id(2).name("Joust").build();
	}

	@Test
	public void testGetAllGameModes() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<GameMode>(gameModeMap.values()));
		assertEquals(MOCK_GAMEMODE_ARRAY, repo.getAllGameModes());
	}

	@Test
	public void testFindGameModeDoesExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		assertEquals(MOCK_GAMEMODE_OBJECT, repo.findGameMode(1));
	}

	@Test
	public void testFindGameModeDoesNotExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		String reply = repo.findGameMode(2);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, reply);
	}

	@Test
	public void testDeleteGameModeWhichDoesExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));

		// Check the result of deleting a mode which does exist
		entityManager.remove(gameModeMap.get(1));
		String reply = repo.deleteGameMode(1);

		assertEquals(Constants.DELETE_GAMEMODE_SUCCESS, reply);
	}

	@Test
	public void testDeleteGameModeWhichDoesNotExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));

		// Check the result of deleting a mode which doesn't exist
		String reply = repo.deleteGameMode(2);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, reply);
	}

	@Test
	public void testCreateGameMode() {
		String reply = repo.createGameMode(MOCK_GAMEMODE_OBJECT);
		assertEquals(Constants.CREATE_GAMEMODE_SUCCESS, reply);
	}

	@Test
	public void testUpdateGameModeWhichDoesExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		Mockito.when(entityManager.merge(gameModeB)).thenReturn(gameModeB);

		String reply = repo.updateGameMode(1, util.getJSONForObject(gameModeB));
		Mockito.verify(entityManager, Mockito.times(1)).merge(gameModeMap.get(1));

		GameMode gameModeFromManager = entityManager.find(GameMode.class, 1);

		assertEquals(gameModeB.getName(), gameModeFromManager.getName());
		assertEquals(Constants.UPDATE_GAMEMODE_SUCCESS, reply);
	}

	@Test
	public void testUpdateGameModeWhichDoesNotExist() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		Mockito.when(entityManager.merge(gameModeB)).thenReturn(gameModeB);
		String reply = repo.updateGameMode(2, util.getJSONForObject(gameModeB));
		assertEquals(Constants.GAMEMODE_NOT_FOUND, reply);
	}

	@Test
	public void testCheckGameModeExists() {
		boolean res;
		res = repo.checkGameModeExists(1);
		assertEquals(false, res);
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		res = repo.checkGameModeExists(1);
		assertEquals(true, res);
	}
}
