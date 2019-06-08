package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.persistence.domain.ChampionGameModeStats;
import com.bae.persistence.domain.DamageType;
import com.bae.persistence.domain.GameMode;
import com.bae.persistence.domain.Pantheon;
import com.bae.util.JSONUtil;

public class ChampionGameModeStatsTest {
	private JSONUtil json;
	private Champion championA;
	private GameMode gameModeA;
	private ChampionGameModeStats cgms;
	private String championGameModeStatsJson;

	@Before
	public void setup() {
		json = new JSONUtil();

		championA = new Champion.Builder()
				.id(1)
				.name("Ymir")
				.pantheon(new Pantheon.Builder()
						.id(1)
						.name("Norse")
						.build())
				.damageType(new DamageType.Builder()
						.id(1)
						.name("Magical")
						.build())
				.health(510)
				.damage(38)
				.build();

		gameModeA = new GameMode.Builder()
				.id(1)
				.name("Conquest")
				.build();

		cgms = new ChampionGameModeStats.Builder()
				.id(1)
				.champion(championA)
				.gameMode(gameModeA)
				.winRate(46.36)
				.pickRate(10.95)
				.banRate(0.70)
				.build();

		championGameModeStatsJson = "{\"id\":1,\"champion\":{\"id\":1,\"name\":\"Ymir\",\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":510,\"damage\":38},\"gameMode\":{\"id\":1,\"name\":\"Conquest\"},\"winRate\":46.36,\"pickRate\":10.95,\"banRate\":0.7}";

	}

	@Test
	public void testChampionGameModeStatToJson() {
		String jsonString = json.getJSONForObject(cgms);
		assertEquals(championGameModeStatsJson, jsonString);
	}

	@Test
	public void testJsonToChampionGameModeStat() {
		ChampionGameModeStats cgmsFromJson = json.getObjectForJSON(championGameModeStatsJson,
				ChampionGameModeStats.class);

		assertEquals("Ymir", cgmsFromJson.getChampion().getName());
		assertEquals("Conquest", cgmsFromJson.getGameMode().getName());
		assertEquals(10.95, cgmsFromJson.getPickRate(), 0.001);
	}

	@Test
	public void testChampionGameModeStatBuilder() {
		ChampionGameModeStats stats = new ChampionGameModeStats.Builder()
				.id(1)
				.champion(championA)
				.gameMode(gameModeA)
				.winRate(46.36)
				.pickRate(10.95)
				.banRate(0.70)
				.build();

		assertEquals(10.95, stats.getPickRate(), 0.001);
		assertEquals(0.70, stats.getBanRate(), 0.001);
		assertEquals(46.36, stats.getWinRate(), 0.001);
		assertEquals(1, stats.getId());
	}

}
