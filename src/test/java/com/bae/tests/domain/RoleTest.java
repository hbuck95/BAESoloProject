package com.bae.tests.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Champion;
import com.bae.persistence.domain.Role;
import com.bae.util.JSONUtil;

public class RoleTest {
	private JSONUtil json;
	private Role roleA;
	private Champion championA;
	private String championAJson;
	private String roleAJson;

	@Before
	public void setup() {
		json = new JSONUtil();

		roleA = new Role.Builder()
				.id(1)
				.name("Guardian")
				.build();

		championA = new Champion.Builder()
				.id(1)
				.name("Ymir")
				.role(roleA)
				.health(510)
				.damage(38)
				.build();

		championAJson = "{\"id\":1,\"name\":\"Ymir\",\"role\":{\"id\":1,\"name\":\"Guardian\"},\"health\":510,\"damage\":38}";
		roleAJson = "{\"id\":1,\"name\":\"Guardian\"}";
	}

	@Test
	public void testRoleToJson() {
		String jsonString = json.getJSONForObject(roleA);
		assertEquals(roleAJson, jsonString);
	}

	@Test
	public void testJsonToRole() {
		Role roleFromJson = json.getObjectForJSON(roleAJson, Role.class);
		assertEquals("Guardian", roleFromJson.getName());
		assertEquals(1, roleFromJson.getId());
	}

	@Test
	public void testRoleWithCharacter() {
		assertEquals("Guardian", championA.getRole().getName());
		assertEquals(1, championA.getRole().getId());
	}

	@Test
	public void testRoleWithCharacterToJson() {
		String jsonString = json.getJSONForObject(championA);
		assertEquals(true, jsonString.contains("Guardian"));
		assertEquals(true, jsonString.contains("Ymir"));
		assertEquals(championAJson, jsonString);
	}

	@Test
	public void testRoleWithCharacterFromJson() {
		Champion champion = json.getObjectForJSON(championAJson, Champion.class);
		assertEquals("Guardian", champion.getRole().getName());
		assertEquals(1, champion.getRole().getId());
		assertEquals("Ymir", champion.getName());
	}
	
	@Test
	public void testRoleBuilder() {
		Role role = new Role.Builder()
				.id(3)
				.name("Assassin")
				.build();
		
		assertEquals(3, role.getId());
		assertEquals("Assassin", role.getName());
				
	}
	
}
