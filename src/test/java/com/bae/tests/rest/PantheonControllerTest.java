package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.PantheonService;
import com.bae.rest.PantheonController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class PantheonControllerTest {

	@InjectMocks
	private PantheonController controller;

	@Mock
	private PantheonService service;

	private static final String MOCK_PANTHEON_ARRAY = "[{\"id\":1,\"name\":\"Norse\"}]";
	private static final String MOCK_PANTHEON_OBJECT = "{\"id\":1,\"name\":\"Norse\"}";

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllPantheons() {
		Mockito.when(controller.getAllPantheons()).thenReturn(MOCK_PANTHEON_ARRAY);
		assertEquals(MOCK_PANTHEON_ARRAY, controller.getAllPantheons());
		Mockito.verify(service).getAllPantheons();
	}

	@Test
	public void testFindPantheonExists() {
		Mockito.when(controller.getPantheon(1)).thenReturn(MOCK_PANTHEON_OBJECT);
		assertEquals(MOCK_PANTHEON_OBJECT, controller.getPantheon(1));
		Mockito.verify(service).findPantheon(1);
	}

	@Test
	public void testFindPantheonDoesNotExist() {
		Mockito.when(controller.getPantheon(1)).thenReturn(Constants.PANTHEON_NOT_FOUND);
		assertEquals(Constants.PANTHEON_NOT_FOUND, controller.getPantheon(1));
		Mockito.verify(service).findPantheon(1);
	}

	@Test
	public void testCreatePantheon() {
		Mockito.when(controller.createPantheon(MOCK_PANTHEON_OBJECT)).thenReturn(Constants.CREATE_PANTHEON_SUCCESS);
		assertEquals(Constants.CREATE_PANTHEON_SUCCESS, controller.createPantheon(MOCK_PANTHEON_OBJECT));
		Mockito.verify(service).createPantheon(MOCK_PANTHEON_OBJECT);
	}

	@Test
	public void testDeletePantheonDoesExist() {
		Mockito.when(controller.deletePantheon(1)).thenReturn(Constants.DELETE_PANTHEON_SUCCESS);
		assertEquals(Constants.DELETE_PANTHEON_SUCCESS, controller.deletePantheon(1));
		Mockito.verify(service).deletePantheon(1);
	}

	@Test
	public void testDeletePantheonDoesNotExist() {
		Mockito.when(controller.deletePantheon(1)).thenReturn(Constants.PANTHEON_NOT_FOUND);
		assertEquals(Constants.PANTHEON_NOT_FOUND, controller.deletePantheon(1));
		Mockito.verify(service).deletePantheon(1);
	}

	@Test
	public void testUpdatePantheonDoesExist() {
		Mockito.when(controller.updatePantheon(1, MOCK_PANTHEON_OBJECT)).thenReturn(Constants.UPDATE_PANTHEON_SUCCESS);
		assertEquals(Constants.UPDATE_PANTHEON_SUCCESS, controller.updatePantheon(1, MOCK_PANTHEON_OBJECT));
		Mockito.verify(service).updatePantheon(1, MOCK_PANTHEON_OBJECT);
	}

	@Test
	public void testUpdatePantheonDoesNotExist() {
		Mockito.when(controller.updatePantheon(1, MOCK_PANTHEON_OBJECT)).thenReturn(Constants.PANTHEON_NOT_FOUND);
		assertEquals(Constants.PANTHEON_NOT_FOUND, controller.updatePantheon(1, MOCK_PANTHEON_OBJECT));
		Mockito.verify(service).updatePantheon(1, MOCK_PANTHEON_OBJECT);
	}

}