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

import com.bae.persistence.domain.Champion;
import com.bae.persistence.repository.ChampionDatabaseRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class ChampionDatabaseRepositoryTest {

	@InjectMocks
	private ChampionDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, Champion> championMap;
	private static final String MOCK_CHAMPION_ARRAY = "[{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}]";
	private static final String MOCK_CHAMPION_OBJECT = "{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}";
	private Champion championB;

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		championMap = new HashMap<>();
		championMap.put(1, util.getObjectForJSON(MOCK_CHAMPION_OBJECT, Champion.class));
		championB = new Champion.Builder().id(2).name("Neith").damage(66).build();
	}

	@Test
	public void testGetAllChampions() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<Champion>(championMap.values()));
		assertEquals(MOCK_CHAMPION_ARRAY, repo.getAllChampions());
	}

	@Test
	public void testFindChampion() {
		Mockito.when(entityManager.find(Champion.class, 1)).thenReturn(championMap.get(1));
		assertEquals(MOCK_CHAMPION_OBJECT, repo.findChampion(1));
	}

	@Test
	public void testDeleteChampionWhichDoesExist() {
		Mockito.when(entityManager.find(Champion.class, 1)).thenReturn(championMap.get(1));

		// Check the result of deleting an entity which does exist
		entityManager.remove(championMap.get(1));
		String reply = repo.deleteChampion(1);

		assertEquals(Constants.DELETE_CHAMPION_SUCCESS, reply);
	}

	@Test
	public void testDeleteChampionWhichDoesNotExist() {
		Mockito.when(entityManager.find(Champion.class, 1)).thenReturn(championMap.get(1));

		// Check the result of deleting an entity which doesn't exist
		String reply = repo.deleteChampion(2);
		assertEquals(Constants.CHAMPION_NOT_FOUND, reply);
	}

}
