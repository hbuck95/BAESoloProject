package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.persistence.domain.Pantheon;
import com.bae.util.JSONUtil;

public class PantheonTest {
	private JSONUtil json;
	private Pantheon pantheonA;
	private Champion championA;
	private String championAJson;
	private String pantheonAJson;

	@Before
	public void setup() {
		json = new JSONUtil();

		pantheonA = new Pantheon.Builder().id(1).name("Norse").build();

		championA = new Champion.Builder().id(1).name("Ymir").pantheon(pantheonA).health(510).damage(38).build();

		championAJson = "{\"id\":1,\"name\":\"Ymir\",\"pantheon\":{\"id\":1,\"name\":\"Norse\"},\"health\":510,\"damage\":38}";
		pantheonAJson = "{\"id\":1,\"name\":\"Norse\"}";
	}

	@Test
	public void testPantheonToJson() {
		String jsonString = json.getJSONForObject(pantheonA);
		assertEquals(pantheonAJson, jsonString);
	}

	@Test
	public void testJsonToPantheon() {
		Pantheon pantheonFromJson = json.getObjectForJSON(pantheonAJson, Pantheon.class);
		assertEquals("Norse", pantheonFromJson.getName());
		assertEquals(1, pantheonFromJson.getId());

	}

	@Test
	public void testChampionWithPantheonToJson() {
		String jsonString = json.getJSONForObject(championA);
		assertEquals(true, jsonString.contains("Norse"));
		assertEquals(true, jsonString.contains("Ymir"));
		assertEquals(championAJson, jsonString);
	}

	@Test
	public void testJsonToChampionWithPantheon() {
		Champion championFromJson = json.getObjectForJSON(championAJson, Champion.class);
		assertEquals("Norse", championFromJson.getPantheon().getName());
		assertEquals(1, championFromJson.getPantheon().getId());
		assertEquals("Ymir", championFromJson.getName());
	}

	@Test
	public void testPantheonBuilder() {
		Pantheon pantheon = new Pantheon.Builder().id(2).name("Egyptian").build();

		assertEquals(2, pantheon.getId());
		assertEquals("Egyptian", pantheon.getName());
	}

	@Test
	public void testSetName() {
		pantheonA.setName("Roman");
		assertEquals("Roman", pantheonA.getName());
		assertEquals("Roman", championA.getPantheon().getName());
	}

	@Test
	public void testGetName() {
		assertEquals("Norse", pantheonA.getName());
		assertEquals("Norse", championA.getPantheon().getName());
	}

	@Test
	public void testGetId() {
		assertEquals(1, pantheonA.getId());
		assertEquals(1, championA.getPantheon().getId());
	}

}
