package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.ChampionServiceImpl;
import com.bae.persistence.repository.ChampionDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ChampionServiceTest {

	@InjectMocks
	private ChampionServiceImpl service;

	@Mock
	private ChampionDatabaseRepository repo;

	private static final String MOCK_CHAMPION_ARRAY = "[{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}]";
	private static final String MOCK_CHAMPION_OBJECT = "{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}";

	@Test
	public void testGetAllChampions() {
		Mockito.when(service.getAllChampions()).thenReturn(MOCK_CHAMPION_ARRAY);
		assertEquals(MOCK_CHAMPION_ARRAY, service.getAllChampions());
	}

	@Test
	public void testFindChampionExists() {
		Mockito.when(service.findChampion(1)).thenReturn(MOCK_CHAMPION_OBJECT);
		assertEquals(MOCK_CHAMPION_OBJECT, service.findChampion(1));
	}

	@Test
	public void testFindChampionDoesNotExist() {
		Mockito.when(service.findChampion(1)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, service.findChampion(1));
		Mockito.verify(repo).findChampion(1);
	}

	@Test
	public void testCreateChampion() {
		Mockito.when(service.createChampion(MOCK_CHAMPION_OBJECT)).thenReturn(Constants.CREATE_CHAMPION_SUCCESS);
		assertEquals(Constants.CREATE_CHAMPION_SUCCESS, service.createChampion(MOCK_CHAMPION_OBJECT));
		Mockito.verify(repo).createChampion(MOCK_CHAMPION_OBJECT);
	}

	@Test
	public void testDeleteChampionDoesExist() {
		Mockito.when(service.deleteChampion(1)).thenReturn(Constants.DELETE_CHAMPION_SUCCESS);
		assertEquals(Constants.DELETE_CHAMPION_SUCCESS, service.deleteChampion(1));
		Mockito.verify(repo).deleteChampion(1);
	}

	@Test
	public void testDeleteChampionDoesNotExist() {
		Mockito.when(service.deleteChampion(1)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, service.deleteChampion(1));
		Mockito.verify(repo).deleteChampion(1);
	}

	@Test
	public void testUpdateChampionDoesExist() {
		Mockito.when(service.updateChampion(1, MOCK_CHAMPION_OBJECT)).thenReturn(Constants.UPDATE_CHAMPION_SUCCESS);
		assertEquals(Constants.UPDATE_CHAMPION_SUCCESS, service.updateChampion(1, MOCK_CHAMPION_OBJECT));
		Mockito.verify(repo).updateChampion(1, MOCK_CHAMPION_OBJECT);
	}

	@Test
	public void testUpdateChampionDoesNotExist() {
		Mockito.when(service.updateChampion(1, MOCK_CHAMPION_OBJECT)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, service.updateChampion(1, MOCK_CHAMPION_OBJECT));
		Mockito.verify(repo).updateChampion(1, MOCK_CHAMPION_OBJECT);
	}
}