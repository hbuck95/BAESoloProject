package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.DamageTypeServiceImpl;
import com.bae.persistence.repository.DamageTypeDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class DamageTypeServiceTest {

	@InjectMocks
	private DamageTypeServiceImpl service;

	@Mock
	private DamageTypeDatabaseRepository repo;

	private static final String MOCK_DAMAGETYPE_ARRAY = "[{\"id\":1,\"name\":\"Magical\"}]";
	private static final String MOCK_DAMAGETYPE_OBJECT = "{\"id\":1,\"name\":\"Magical\"}";

	@Test
	public void testGetAllDamageTypes() {
		Mockito.when(service.getAllDamageTypes()).thenReturn(MOCK_DAMAGETYPE_ARRAY);
		assertEquals(MOCK_DAMAGETYPE_ARRAY, service.getAllDamageTypes());
	}

	@Test
	public void testFindDamageTypeExists() {
		Mockito.when(service.findDamageType(1)).thenReturn(MOCK_DAMAGETYPE_OBJECT);
		assertEquals(MOCK_DAMAGETYPE_OBJECT, service.findDamageType(1));
	}

	@Test
	public void testFindDamageTypeDoesNotExist() {
		Mockito.when(service.findDamageType(1)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, service.findDamageType(1));
		Mockito.verify(repo).findDamageType(1);
	}

	@Test
	public void testCreateDamageType() {
		Mockito.when(service.createDamageType(MOCK_DAMAGETYPE_OBJECT)).thenReturn(Constants.CREATE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.CREATE_DAMAGETYPE_SUCCESS, service.createDamageType(MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(repo).createDamageType(MOCK_DAMAGETYPE_OBJECT);
	}

	@Test
	public void testDeleteDamageTypeDoesExist() {
		Mockito.when(service.deleteDamageType(1)).thenReturn(Constants.DELETE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.DELETE_DAMAGETYPE_SUCCESS, service.deleteDamageType(1));
		Mockito.verify(repo).deleteDamageType(1);
	}

	@Test
	public void testDeleteDamageTypeDoesNotExist() {
		Mockito.when(service.deleteDamageType(1)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, service.deleteDamageType(1));
		Mockito.verify(repo).deleteDamageType(1);
	}

	@Test
	public void testUpdateDamageTypeDoesExist() {
		Mockito.when(service.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT))
				.thenReturn(Constants.UPDATE_DAMAGETYPE_SUCCESS);
		assertEquals(Constants.UPDATE_DAMAGETYPE_SUCCESS, service.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(repo).updateDamageType(1, MOCK_DAMAGETYPE_OBJECT);
	}

	@Test
	public void testUpdateDamageTypeDoesNotExist() {
		Mockito.when(service.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT)).thenReturn(Constants.DAMAGETYPE_NOT_FOUND);
		assertEquals(Constants.DAMAGETYPE_NOT_FOUND, service.updateDamageType(1, MOCK_DAMAGETYPE_OBJECT));
		Mockito.verify(repo).updateDamageType(1, MOCK_DAMAGETYPE_OBJECT);
	}
}
