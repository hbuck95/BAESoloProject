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

}
