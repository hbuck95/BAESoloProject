package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.GameModeService;
import com.bae.rest.GameModeController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class GameModeControllerTest {

	@InjectMocks
	private GameModeController controller;

	@Mock
	private GameModeService service;

	private static final String MOCK_GAMEMODE_ARRAY = "[{\"id\":1,\"name\":\"Conquest\"}]";
	private static final String MOCK_GAMEMODE_OBJECT = "{\"id\":1,\"name\":\"Conquest\"}";

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllGameModes() {
		Mockito.when(service.getAllGameModes()).thenReturn(MOCK_GAMEMODE_ARRAY);
		assertEquals(MOCK_GAMEMODE_ARRAY, controller.getAllGameModes());
		Mockito.verify(service).getAllGameModes();
	}

	@Test
	public void testFindGameModeExists() {
		Mockito.when(service.findGameMode(1)).thenReturn(MOCK_GAMEMODE_OBJECT);
		assertEquals(MOCK_GAMEMODE_OBJECT, controller.getGameMode(1));
		Mockito.verify(service).findGameMode(1);
	}

	@Test
	public void testFindGameModeDoesNotExist() {
		Mockito.when(service.findGameMode(1)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, controller.getGameMode(1));
		Mockito.verify(service).findGameMode(1);
	}

	@Test
	public void testCreateGameMode() {
		Mockito.when(controller.createGameMode(MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.CREATE_GAMEMODE_SUCCESS);
		assertEquals(Constants.CREATE_GAMEMODE_SUCCESS, controller.createGameMode(MOCK_GAMEMODE_OBJECT));
		Mockito.verify(service).createGameMode(MOCK_GAMEMODE_OBJECT);
	}

	@Test
	public void testDeleteGameModeDoesExist() {
		Mockito.when(controller.deleteGameMode(1)).thenReturn(Constants.DELETE_GAMEMODE_SUCCESS);
		assertEquals(Constants.DELETE_GAMEMODE_SUCCESS, controller.deleteGameMode(1));
		Mockito.verify(service).deleteGameMode(1);
	}

	@Test
	public void testDeleteGameModeDoesNotExist() {
		Mockito.when(controller.deleteGameMode(1)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, controller.deleteGameMode(1));
		Mockito.verify(service).deleteGameMode(1);
	}

	@Test
	public void testUpdateGameModeDoesExist() {
		Mockito.when(controller.updateGameMode(1, MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.UPDATE_GAMEMODE_SUCCESS);
		assertEquals(Constants.UPDATE_GAMEMODE_SUCCESS, controller.updateGameMode(1, MOCK_GAMEMODE_OBJECT));
		Mockito.verify(service).updateGameMode(1, MOCK_GAMEMODE_OBJECT);
	}

	@Test
	public void testUpdateGameModeDoesNotExist() {
		Mockito.when(controller.updateGameMode(1, MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, controller.updateGameMode(1, MOCK_GAMEMODE_OBJECT));
		Mockito.verify(service).updateGameMode(1, MOCK_GAMEMODE_OBJECT);
	}

}