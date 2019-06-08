package com.bae.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bae.util.JSONUtil;

public class JSONUtilTest {

	private JSONUtil json;
	private Map<Integer, String> values;
	private String valuesAsJson;

	@Before
	public void setup() {
		json = new JSONUtil();
		values = new HashMap<>();
		values.put(1, "One");
		values.put(2, "Two");
		values.put(3, "Three");
		valuesAsJson = "{\"1\":\"One\",\"2\":\"Two\",\"3\":\"Three\"}";
	}

	@Test
	public void testObjectToJson() {
		String jsonString = json.getJSONForObject(values);
		assertEquals(valuesAsJson, jsonString);
	}

	@Test
	public void testJsonToObject() {
		Map<Integer, String> mapFromJson = json.getObjectForJSON(valuesAsJson, values.getClass());
		assertEquals(true, mapFromJson.containsValue("One"));
		assertEquals(values.values().toString(), mapFromJson.values().toString());
	}

}
