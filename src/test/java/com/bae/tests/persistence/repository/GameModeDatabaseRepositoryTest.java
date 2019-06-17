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
	public void testFindGameMode() {
		Mockito.when(entityManager.find(GameMode.class, 1)).thenReturn(gameModeMap.get(1));
		assertEquals(MOCK_GAMEMODE_OBJECT, repo.findGameMode(1));
	}

}
