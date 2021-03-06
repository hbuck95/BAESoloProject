package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.GameMode;
import com.bae.util.JSONUtil;

public class GameModeTest {
	private JSONUtil json;
	private GameMode gameMode;
	private String gameModeAJson;

	@Before
	public void setup() {
		json = new JSONUtil();
		gameMode = new GameMode.Builder().id(1).name("Conquest").build();
		gameModeAJson = "{\"id\":1,\"name\":\"Conquest\"}";
	}

	@Test
	public void testGameModeToJson() {
		String jsonString = json.getJSONForObject(gameMode);
		assertEquals(gameModeAJson, jsonString);
	}

	@Test
	public void testJsonToGameMode() {
		GameMode gameModeFromJson = json.getObjectForJSON(gameModeAJson, GameMode.class);
		assertEquals("Conquest", gameModeFromJson.getName());
		assertEquals(1, gameModeFromJson.getId());
	}

	@Test
	public void testGameModeBuilder() {
		GameMode mode = new GameMode.Builder().id(2).name("Joust").build();

		assertEquals(2, mode.getId());
		assertEquals("Joust", mode.getName());
	}

	@Test
	public void testSetName() {
		gameMode.setName("Assault");
		assertEquals("Assault", gameMode.getName());
	}

	@Test
	public void testGetName() {
		assertEquals("Conquest", gameMode.getName());
	}

	@Test
	public void testGetId() {
		assertEquals(1, gameMode.getId());
	}
}
