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

import com.bae.persistence.domain.ChampionGameModeStats;
import com.bae.persistence.repository.ChampionGameModeStatsDatabaseRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class ChampionGameModeStatsDatabaseRepositoryTest {

	@InjectMocks
	private ChampionGameModeStatsDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, ChampionGameModeStats> statMap;
	private static final String MOCK_STAT_ARRAY = "[{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}]";
	private static final String MOCK_STAT_OBJECT = "{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}";
	private ChampionGameModeStats statsB;

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		statMap = new HashMap<>();
		statMap.put(1, util.getObjectForJSON(MOCK_STAT_OBJECT, ChampionGameModeStats.class));
		statsB = new ChampionGameModeStats.Builder().id(2).pickRate(36.55).banRate(17.89).winRate(54.22).build();
	}

	@Test
	public void testGetAllStats() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<ChampionGameModeStats>(statMap.values()));
		assertEquals(MOCK_STAT_ARRAY, repo.getAllChampionGameModeStats());
	}

	@Test
	public void testFindStats() {
		Mockito.when(entityManager.find(ChampionGameModeStats.class, 1)).thenReturn(statMap.get(1));
		assertEquals(MOCK_STAT_OBJECT, repo.findChampionGameModeStats(1));
	}

	@Test
	public void testDeleteStatsWhichDoExist() {
		Mockito.when(entityManager.find(ChampionGameModeStats.class, 1)).thenReturn(statMap.get(1));

		// Check the result of deleting a mode which does exist
		entityManager.remove(statMap.get(1));
		String reply = repo.deleteChampionGameModeStats(1);

		assertEquals(Constants.DELETE_STATS_SUCCESS, reply);
	}

	@Test
	public void testDeleteStatsWhichDoNotExist() {
		Mockito.when(entityManager.find(ChampionGameModeStats.class, 1)).thenReturn(statMap.get(1));

		// Check the result of deleting a mode which doesn't exist
		String reply = repo.deleteChampionGameModeStats(2);
		assertEquals(Constants.STATS_NOT_FOUND, reply);
	}

	@Test
	public void testCreateStats() {
		String reply = repo.createChampionGameModeStats(MOCK_STAT_OBJECT);
		assertEquals(Constants.CREATE_STATS_SUCCESS, reply);
	}

	@Test
	public void testUpdateStatsWhichDoExist() {
		Mockito.when(entityManager.find(ChampionGameModeStats.class, 1)).thenReturn(statMap.get(1));
		Mockito.when(entityManager.merge(statsB)).thenReturn(statsB);

		String reply = repo.updateChampionGameModeStats(1, util.getJSONForObject(statsB));
		Mockito.verify(entityManager, Mockito.times(1)).merge(statMap.get(1));

		ChampionGameModeStats statsFromManager = entityManager.find(ChampionGameModeStats.class, 1);

		assertEquals(statsB.getBanRate(), statsFromManager.getBanRate(), 0.001);
		assertEquals(statsB.getPickRate(), statsFromManager.getPickRate(), 0.001);
		assertEquals(statsB.getWinRate(), statsFromManager.getWinRate(), 0.001);
		assertEquals(Constants.UPDATE_STATS_SUCCESS, reply);
	}

	@Test
	public void testUpdateStatsWhichDoNotExist() {
		Mockito.when(entityManager.find(ChampionGameModeStats.class, 1)).thenReturn(statMap.get(1));
		Mockito.when(entityManager.merge(statsB)).thenReturn(statsB);
		String reply = repo.updateChampionGameModeStats(2, util.getJSONForObject(statsB));
		assertEquals(Constants.STATS_NOT_FOUND, reply);
	}

}
