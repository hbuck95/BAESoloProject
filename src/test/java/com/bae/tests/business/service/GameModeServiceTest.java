package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.GameModeServiceImpl;
import com.bae.persistence.repository.GameModeDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class GameModeServiceTest {

	@InjectMocks
	private GameModeServiceImpl service;

	@Mock
	private GameModeDatabaseRepository repo;

	private static final String MOCK_GAMEMODE_ARRAY = "[{\"id\":1,\"name\":\"Conquest\"}]";
	private static final String MOCK_GAMEMODE_OBJECT = "{\"id\":1,\"name\":\"Conquest\"}";

	@Test
	public void testGetAllGameModes() {
		Mockito.when(service.getAllGameModes()).thenReturn(MOCK_GAMEMODE_ARRAY);
		assertEquals(MOCK_GAMEMODE_ARRAY, service.getAllGameModes());
	}

	@Test
	public void testFindGameModeExists() {
		Mockito.when(service.findGameMode(1)).thenReturn(MOCK_GAMEMODE_OBJECT);
		assertEquals(MOCK_GAMEMODE_OBJECT, service.findGameMode(1));
	}

	@Test
	public void testFindGameModeDoesNotExist() {
		Mockito.when(service.findGameMode(1)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, service.findGameMode(1));
		Mockito.verify(repo).findGameMode(1);
	}

	@Test
	public void testCreateGameMode() {
		Mockito.when(service.createGameMode(MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.CREATE_GAMEMODE_SUCCESS);
		assertEquals(Constants.CREATE_GAMEMODE_SUCCESS, service.createGameMode(MOCK_GAMEMODE_OBJECT));
		Mockito.verify(repo).createGameMode(MOCK_GAMEMODE_OBJECT);
	}

	@Test
	public void testDeleteGameModeDoesExist() {
		Mockito.when(service.deleteGameMode(1)).thenReturn(Constants.DELETE_GAMEMODE_SUCCESS);
		assertEquals(Constants.DELETE_GAMEMODE_SUCCESS, service.deleteGameMode(1));
		Mockito.verify(repo).deleteGameMode(1);
	}

	@Test
	public void testDeleteGameModeDoesNotExist() {
		Mockito.when(service.deleteGameMode(1)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, service.deleteGameMode(1));
		Mockito.verify(repo).deleteGameMode(1);
	}

	@Test
	public void testUpdateGameModeDoesExist() {
		Mockito.when(service.updateGameMode(1, MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.UPDATE_GAMEMODE_SUCCESS);
		assertEquals(Constants.UPDATE_GAMEMODE_SUCCESS, service.updateGameMode(1, MOCK_GAMEMODE_OBJECT));
		Mockito.verify(repo).updateGameMode(1, MOCK_GAMEMODE_OBJECT);
	}

	@Test
	public void testUpdateGameModeDoesNotExist() {
		Mockito.when(service.updateGameMode(1, MOCK_GAMEMODE_OBJECT)).thenReturn(Constants.GAMEMODE_NOT_FOUND);
		assertEquals(Constants.GAMEMODE_NOT_FOUND, service.updateGameMode(1, MOCK_GAMEMODE_OBJECT));
		Mockito.verify(repo).updateGameMode(1, MOCK_GAMEMODE_OBJECT);
	}
}
