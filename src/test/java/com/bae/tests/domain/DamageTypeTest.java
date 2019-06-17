package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.persistence.domain.DamageType;
import com.bae.util.JSONUtil;

public class DamageTypeTest {
	private JSONUtil json;
	private DamageType damageTypeA;
	private Champion championA;
	private String championAJson;
	private String damageTypeAJson;

	@Before
	public void setup() {
		json = new JSONUtil();

		damageTypeA = new DamageType.Builder().id(1).name("Magical").build();

		championA = new Champion.Builder().id(1).name("Ymir").damageType(damageTypeA).health(510).damage(38).build();

		championAJson = "{\"id\":1,\"name\":\"Ymir\",\"damageType\":{\"id\":1,\"name\":\"Magical\"},\"health\":510,\"damage\":38}";
		damageTypeAJson = "{\"id\":1,\"name\":\"Magical\"}";

	}

	@Test
	public void testDamageTypeToJson() {
		String jsonString = json.getJSONForObject(damageTypeA);
		assertEquals(damageTypeAJson, jsonString);
	}

	@Test
	public void testJsonToDamageType() {
		DamageType damageTypeFromJson = json.getObjectForJSON(damageTypeAJson, DamageType.class);
		assertEquals("Magical", damageTypeFromJson.getName());
		assertEquals(1, damageTypeFromJson.getId());

	}

	@Test
	public void testChampionWithDamageTypeToJson() {
		String jsonString = json.getJSONForObject(championA);
		assertEquals(true, jsonString.contains("Magical"));
		assertEquals(true, jsonString.contains("Ymir"));
		assertEquals(championAJson, jsonString);
	}

	@Test
	public void testJsonToChampionWithDamageType() {
		Champion championFromJson = json.getObjectForJSON(championAJson, Champion.class);
		assertEquals("Magical", championFromJson.getDamageType().getName());
		assertEquals(1, championFromJson.getDamageType().getId());
		assertEquals("Ymir", championFromJson.getName());
	}

	@Test
	public void testDamageTypeBuilder() {
		DamageType damageType = new DamageType.Builder().id(2).name("Physical").build();

		assertEquals(2, damageType.getId());
		assertEquals("Physical", damageType.getName());
	}

	@Test
	public void testSetName() {
		championA.getDamageType().setName("Melee");
		assertEquals("Melee", championA.getDamageType().getName());
	}

	@Test
	public void testGetName() {
		assertEquals("Magical", championA.getDamageType().getName());
	}

	@Test
	public void testGetId() {
		assertEquals(1, championA.getDamageType().getId());
	}

}
