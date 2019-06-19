package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.persistence.domain.DamageType;
import com.bae.persistence.domain.Pantheon;
import com.bae.persistence.domain.Role;
import com.bae.util.JSONUtil;

public class ChampionTest {
	private Champion championA;
	private JSONUtil json;
	private String championAJson;

	@Before
	public void setup() {
		json = new JSONUtil();
		championA = new Champion.Builder().id(1).name("Ymir").health(510).damage(38).build();
		championAJson = "{\"id\":1,\"name\":\"Ymir\",\"health\":510,\"damage\":38}";
	}

	@Test
	public void testChampionToJson() {
		String jsonString = json.getJSONForObject(championA);
		assertEquals(true, jsonString.contains("Ymir"));
		assertEquals(championAJson, jsonString);
	}

	@Test
	public void testJsonToChampion() {
		Champion champFromJson = json.getObjectForJSON(championAJson, Champion.class);
		assertEquals(championA.getName(), champFromJson.getName());
		assertEquals(championA.getDamage(), champFromJson.getDamage());
	}

	@Test
	public void testChampionBuilder() {
		Champion neith = new Champion.Builder().id(2).name("Neith").health(435).damage(38).build();

		assertEquals(2, neith.getId());
		assertEquals("Neith", neith.getName());
		assertEquals(435, neith.getHealth());
		assertEquals(38, neith.getDamage());
	}

	@Test
	public void testSetName() {
		championA.setName("Fred");
		assertEquals("Fred", championA.getName());
	}

	@Test
	public void testSetHealth() {
		championA.setHealth(450);
		assertEquals(450, championA.getHealth());
	}

	@Test
	public void testSetDamage() {
		championA.setDamage(40);
		assertEquals(40, championA.getDamage());
	}

	@Test
	public void testGetName() {
		assertEquals("Ymir", championA.getName());
	}

	@Test
	public void testGetHealth() {
		assertEquals(510, championA.getHealth());
	}

	@Test
	public void testGetDamage() {
		assertEquals(38, championA.getDamage());
	}

	@Test
	public void testGetId() {
		assertEquals(1, championA.getId());
	}

	@Test
	public void testSetPantheon() {
		Pantheon p = new Pantheon.Builder().id(3).name("Roman").build();
		championA.setPantheon(p);
		assertEquals(3, championA.getPantheon().getId());
		assertEquals("Roman", championA.getPantheon().getName());
	}

	@Test
	public void testSetRole() {
		Role r = new Role.Builder().id(1).name("Guardian").build();
		championA.setRole(r);
		assertEquals(1, championA.getRole().getId());
		assertEquals("Guardian", championA.getRole().getName());
	}

	@Test
	public void testSetDamageType() {
		DamageType dt = new DamageType.Builder().id(1).name("Magical").build();
		championA.setDamageType(dt);
		assertEquals(1, championA.getDamageType().getId());
		assertEquals("Magical", championA.getDamageType().getName());
	}

}
