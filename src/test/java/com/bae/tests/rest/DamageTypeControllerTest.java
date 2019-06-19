package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.DamageTypeService;
import com.bae.rest.DamageTypeController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class DamageTypeControllerTest {

	@InjectMocks
	private DamageTypeController controller;

	@Mock
	private DamageTypeService service;

	private static final String MOCK_DAMAGETYPE_ARRAY = "[{\"id\":1,\"name\":\"Magical\"}]";
	private static final String MOCK_DAMAGETYPE_OBJECT = "{\"id\":1,\"name\":\"Magical\"}";

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllDamageTypes() {
		Mockito.when(service.getAllDamageTypes()).thenReturn(MOCK_DAMAGETYPE_ARRAY);
		assertEquals(MOCK_DAMAGETYPE_ARRAY, controller.getAllDamageTypes());
		Mockito.verify(service).getAllDamageTypes();
	}

	@Test
	public void testFindDamageTypeExists() {
		Mockito.when(service.findDamageType(1)).thenReturn(MOCK_DAMAGETYPE_OBJECT);
		assertEquals(MOCK_DAMAGETYPE_OBJECT, controller.findDamageType(1));
		Mockito.verify(service).findDamageType(1);
	}

	@Test
	public void testFindDamageTypeDoesNotExist() {
		Mockito.when(service.findDamageType(1)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, controller.findDamageType(1));
		Mockito.verify(service).findDamageType(1);
	}

	@Test
	public void testCreateDamageType() {
		Mockito.when(controller.createDamageType(MOCK_DAMAGETYPE_OBJECT))
				.thenReturn(Constants.CREATE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.CREATE_DAMAGETYPE_SUCCESS, controller.createDamageType(MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(service).createDamageType(MOCK_DAMAGETYPE_OBJECT);
	}

	@Test
	public void testDeleteDamageTypeDoesExist() {
		Mockito.when(controller.deleteDamageType(1)).thenReturn(Constants.DELETE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.DELETE_DAMAGETYPE_SUCCESS, controller.deleteDamageType(1));
		Mockito.verify(service).deleteDamageType(1);
	}

	@Test
	public void testDeleteDamageTypeDoesNotExist() {
		Mockito.when(controller.deleteDamageType(1)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, controller.deleteDamageType(1));
		Mockito.verify(service).deleteDamageType(1);
	}

	@Test
	public void testUpdateDamageTypeDoesExist() {
		Mockito.when(controller.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT))
				.thenReturn(Constants.UPDATE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.UPDATE_DAMAGETYPE_SUCCESS, controller.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(service).updateDamageType(1, MOCK_DAMAGETYPE_OBJECT);
	}

	@Test
	public void testUpdateDamageTypeDoesNotExist() {
		Mockito.when(controller.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, controller.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(service).updateDamageType(1, MOCK_DAMAGETYPE_OBJECT);
	}

}
