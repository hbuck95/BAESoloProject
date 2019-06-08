package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.util.JSONUtil;

public class ChampionTest {
	private Champion championA;
	private Champion championB;
	private JSONUtil json;
	private String championAJson;

	@Before
	public void setup() {
		json = new JSONUtil();
		championA = new Champion.ChampionBuilder()
				.id(1)
				.name("Ymir")
				.pantheon(1)
				.damageType(1)
				.health(510)
				.damage(38)
				.build();

		championB = new Champion.ChampionBuilder()
				.id(2)
				.name("Neith")
				.pantheon(2)
				.damageType(2)
				.health(435)
				.damage(38)
				.build();

		championAJson = "{\"id\":1,\"name\":\"Ymir\",\"pantheonId\":1,\"damageType\":1,\"health\":510,\"damage\":38}";
	}

	@Test
	public void TestChampionToJson() {
		String jsonString = json.getJSONForObject(championA);
		assertEquals(true, jsonString.contains("Ymir"));
		assertEquals(championAJson, jsonString);
	}

	@Test
	public void TestJsonToChampion() {
		Champion champFromJson = json.getObjectForJSON(championAJson, Champion.class);
		assertEquals(championA.getName(), champFromJson.getName());
		assertEquals(championA.getDamage(), champFromJson.getDamage());
	}

	@Test
	public void TestChampionBuilder() {
		Champion Neith = new Champion.ChampionBuilder()
				.id(2)
				.name("Neith")
				.pantheon(2)
				.damageType(2)
				.health(435)
				.damage(38)
				.build();

		assertEquals(Champion.class, Neith.getClass());
		assertEquals(championB.getName(), Neith.getName());
	}

}
