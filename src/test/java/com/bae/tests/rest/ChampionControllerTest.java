package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.ChampionService;
import com.bae.rest.ChampionController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ChampionControllerTest {

	@InjectMocks
	private ChampionController controller;

	@Mock
	private ChampionService service;

	private static final String MOCK_CHAMPION_ARRAY = "[{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}]";
	private static final String MOCK_CHAMPION_OBJECT = "{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":550,\"damage\":69}";

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllChampions() {
		Mockito.when(service.getAllChampions()).thenReturn(MOCK_CHAMPION_ARRAY);
		assertEquals(MOCK_CHAMPION_ARRAY, controller.getAllChampions());
		Mockito.verify(service).getAllChampions();
	}

	@Test
	public void testFindChampionExists() {
		Mockito.when(service.findChampion(1)).thenReturn(MOCK_CHAMPION_OBJECT);
		assertEquals(MOCK_CHAMPION_OBJECT, controller.findChampion(1));
		Mockito.verify(service).findChampion(1);
	}

	@Test
	public void testFindChampionDoesNotExist() {
		Mockito.when(service.findChampion(1)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, controller.findChampion(1));
		Mockito.verify(service).findChampion(1);
	}

	@Test
	public void testCreateChampion() {
		Mockito.when(controller.createChampion(MOCK_CHAMPION_OBJECT)).thenReturn(Constants.CREATE_CHAMPION_SUCCESS);
		assertEquals(Constants.CREATE_CHAMPION_SUCCESS, controller.createChampion(MOCK_CHAMPION_OBJECT));
		Mockito.verify(service).createChampion(MOCK_CHAMPION_OBJECT);
	}

	@Test
	public void testDeleteChampionDoesExist() {
		Mockito.when(controller.deleteChampion(1)).thenReturn(Constants.DELETE_CHAMPION_SUCCESS);
		assertEquals(Constants.DELETE_CHAMPION_SUCCESS, controller.deleteChampion(1));
		Mockito.verify(service).deleteChampion(1);
	}

	@Test
	public void testDeleteChampionDoesNotExist() {
		Mockito.when(controller.deleteChampion(1)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, controller.deleteChampion(1));
		Mockito.verify(service).deleteChampion(1);
	}

	@Test
	public void testUpdateChampionDoesExist() {
		Mockito.when(controller.updateChampion(1, MOCK_CHAMPION_OBJECT)).thenReturn(Constants.UPDATE_CHAMPION_SUCCESS);
		assertEquals(Constants.UPDATE_CHAMPION_SUCCESS, controller.updateChampion(1, MOCK_CHAMPION_OBJECT));
		Mockito.verify(service).updateChampion(1, MOCK_CHAMPION_OBJECT);
	}

	@Test
	public void testUpdateChampionDoesNotExist() {
		Mockito.when(controller.updateChampion(1, MOCK_CHAMPION_OBJECT)).thenReturn(Constants.CHAMPION_NOT_FOUND);
		assertEquals(Constants.CHAMPION_NOT_FOUND, controller.updateChampion(1, MOCK_CHAMPION_OBJECT));
		Mockito.verify(service).updateChampion(1, MOCK_CHAMPION_OBJECT);
	}

}
