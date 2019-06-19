package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.ChampionGameModeStatsServiceImpl;
import com.bae.persistence.repository.ChampionGameModeStatsDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ChampionGameModeStatsServiceTest {

	@InjectMocks
	private ChampionGameModeStatsServiceImpl service;

	@Mock
	private ChampionGameModeStatsDatabaseRepository repo;

	private static final String MOCK_STATS_ARRAY = "[{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}]";
	private static final String MOCK_STATS_OBJECT = "{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}";

	@Test
	public void testGetAllChampionGameModeStatss() {
		Mockito.when(service.getAllChampionGameModeStats()).thenReturn(MOCK_STATS_ARRAY);
		assertEquals(MOCK_STATS_ARRAY, service.getAllChampionGameModeStats());
	}

	@Test
	public void testFindChampionGameModeStatsExists() {
		Mockito.when(service.findChampionGameModeStats(1)).thenReturn(MOCK_STATS_OBJECT);
		assertEquals(MOCK_STATS_OBJECT, service.findChampionGameModeStats(1));
	}

	@Test
	public void testFindChampionGameModeStatsDoesNotExist() {
		Mockito.when(service.findChampionGameModeStats(1)).thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, service.findChampionGameModeStats(1));
		Mockito.verify(repo).findChampionGameModeStats(1);
	}

	@Test
	public void testCreateChampionGameModeStats() {
		Mockito.when(service.createChampionGameModeStats(MOCK_STATS_OBJECT)).thenReturn(Constants.CREATE_STATS_SUCCESS);
		assertEquals(Constants.CREATE_STATS_SUCCESS, service.createChampionGameModeStats(MOCK_STATS_OBJECT));
		Mockito.verify(repo).createChampionGameModeStats(MOCK_STATS_OBJECT);
	}

	@Test
	public void testDeleteChampionGameModeStatsDoesExist() {
		Mockito.when(service.deleteChampionGameModeStats(1)).thenReturn(Constants.DELETE_STATS_SUCCESS);
		assertEquals(Constants.DELETE_STATS_SUCCESS, service.deleteChampionGameModeStats(1));
		Mockito.verify(repo).deleteChampionGameModeStats(1);
	}

	@Test
	public void testDeleteChampionGameModeStatsDoesNotExist() {
		Mockito.when(service.deleteChampionGameModeStats(1)).thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, service.deleteChampionGameModeStats(1));
		Mockito.verify(repo).deleteChampionGameModeStats(1);
	}

	@Test
	public void testUpdateChampionGameModeStatsDoesExist() {
		Mockito.when(service.updateChampionGameModeStats(1, MOCK_STATS_OBJECT))
				.thenReturn(Constants.UPDATE_STATS_SUCCESS);
		assertEquals(Constants.UPDATE_STATS_SUCCESS, service.updateChampionGameModeStats(1, MOCK_STATS_OBJECT));
		Mockito.verify(repo).updateChampionGameModeStats(1, MOCK_STATS_OBJECT);
	}

	@Test
	public void testUpdateChampionGameModeStatsDoesNotExist() {
		Mockito.when(service.updateChampionGameModeStats(1, MOCK_STATS_OBJECT)).thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, service.updateChampionGameModeStats(1, MOCK_STATS_OBJECT));
		Mockito.verify(repo).updateChampionGameModeStats(1, MOCK_STATS_OBJECT);
	}
}