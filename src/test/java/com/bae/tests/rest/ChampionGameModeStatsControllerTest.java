package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.ChampionGameModeStatsService;
import com.bae.rest.ChampionGameModeStatsController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ChampionGameModeStatsControllerTest {

	@InjectMocks
	private ChampionGameModeStatsController controller;

	@Mock
	private ChampionGameModeStatsService service;

	private static final String MOCK_STATS_ARRAY = "[{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}]";
	private static final String MOCK_STATS_OBJECT = "{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}";

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllChampionGameModeStatss() {
		Mockito.when(service.getAllChampionGameModeStats()).thenReturn(MOCK_STATS_ARRAY);
		assertEquals(MOCK_STATS_ARRAY, controller.getAllChampionGameModeStats());
		Mockito.verify(service).getAllChampionGameModeStats();
	}

	@Test
	public void testFindChampionGameModeStatsExists() {
		Mockito.when(service.findChampionGameModeStats(1)).thenReturn(MOCK_STATS_OBJECT);
		assertEquals(MOCK_STATS_OBJECT, controller.findChampionGameModeStats(1));
		Mockito.verify(service).findChampionGameModeStats(1);
	}

	@Test
	public void testFindChampionGameModeStatsDoesNotExist() {
		Mockito.when(service.findChampionGameModeStats(1)).thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, controller.findChampionGameModeStats(1));
		Mockito.verify(service).findChampionGameModeStats(1);
	}

	@Test
	public void testCreateChampionGameModeStats() {
		Mockito.when(controller.createChampionGameModeStats(MOCK_STATS_OBJECT))
				.thenReturn(Constants.CREATE_STATS_SUCCESS);
		assertEquals(Constants.CREATE_STATS_SUCCESS, controller.createChampionGameModeStats(MOCK_STATS_OBJECT));
		Mockito.verify(service).createChampionGameModeStats(MOCK_STATS_OBJECT);
	}

	@Test
	public void testDeleteChampionGameModeStatsDoesExist() {
		Mockito.when(controller.deleteChampionGameModeStats(1)).thenReturn(Constants.DELETE_STATS_SUCCESS);
		assertEquals(Constants.DELETE_STATS_SUCCESS, controller.deleteChampionGameModeStats(1));
		Mockito.verify(service).deleteChampionGameModeStats(1);
	}

	@Test
	public void testDeleteChampionGameModeStatsDoesNotExist() {
		Mockito.when(controller.deleteChampionGameModeStats(1)).thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, controller.deleteChampionGameModeStats(1));
		Mockito.verify(service).deleteChampionGameModeStats(1);
	}

	@Test
	public void testUpdateChampionGameModeStatsDoesExist() {
		Mockito.when(controller.updateChampionGameModeStats(1, MOCK_STATS_OBJECT))
				.thenReturn(Constants.UPDATE_STATS_SUCCESS);
		assertEquals(Constants.UPDATE_STATS_SUCCESS, controller.updateChampionGameModeStats(1, MOCK_STATS_OBJECT));
		Mockito.verify(service).updateChampionGameModeStats(1, MOCK_STATS_OBJECT);
	}

	@Test
	public void testUpdateChampionGameModeStatsDoesNotExist() {
		Mockito.when(controller.updateChampionGameModeStats(1, MOCK_STATS_OBJECT))
				.thenReturn(Constants.STATS_NOT_FOUND);
		assertEquals(Constants.STATS_NOT_FOUND, controller.updateChampionGameModeStats(1, MOCK_STATS_OBJECT));
		Mockito.verify(service).updateChampionGameModeStats(1, MOCK_STATS_OBJECT);
	}

}
