package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.PantheonServiceImpl;
import com.bae.persistence.repository.PantheonDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class PantheonServiceTest {

	@InjectMocks
	private PantheonServiceImpl service;

	@Mock
	private PantheonDatabaseRepository repo;

	private static final String MOCK_PANTHEON_ARRAY = "[{\"id\":1,\"name\":\"Norse\"}]";
	private static final String MOCK_PANTHEON_OBJECT = "{\"id\":1,\"name\":\"Norse\"}";

	@Test
	public void testGetAllPantheons() {
		Mockito.when(service.getAllPantheons()).thenReturn(MOCK_PANTHEON_ARRAY);
		assertEquals(MOCK_PANTHEON_ARRAY, service.getAllPantheons());
	}

	@Test
	public void testFindPantheonExists() {
		Mockito.when(service.findPantheon(1)).thenReturn(MOCK_PANTHEON_OBJECT);
		assertEquals(MOCK_PANTHEON_OBJECT, service.findPantheon(1));
	}

	@Test
	public void testFindPantheonDoesNotExist() {
		Mockito.when(service.findPantheon(1)).thenReturn(Constants.PANTHEON_NOT_FOUND);
		assertEquals(Constants.PANTHEON_NOT_FOUND, service.findPantheon(1));
		Mockito.verify(repo).findPantheon(1);
	}

	@Test
	public void testCreatePantheon() {
		Mockito.when(service.createPantheon(MOCK_PANTHEON_OBJECT)).thenReturn(Constants.CREATE_PANTHEON_SUCCESS);
		assertEquals(Constants.CREATE_PANTHEON_SUCCESS, service.createPantheon(MOCK_PANTHEON_OBJECT));
		Mockito.verify(repo).createPantheon(MOCK_PANTHEON_OBJECT);
	}

	@Test
	public void testDeletePantheonDoesExist() {
		Mockito.when(service.deletePantheon(1)).thenReturn(Constants.DELETE_PANTHEON_SUCCESS);
		assertEquals(Constants.DELETE_PANTHEON_SUCCESS, service.deletePantheon(1));
		Mockito.verify(repo).deletePantheon(1);
	}

	@Test
	public void testDeletePantheonDoesNotExist() {
		Mockito.when(service.deletePantheon(1)).thenReturn(Constants.PANTHEON_NOT_FOUND);
		assertEquals(Constants.PANTHEON_NOT_FOUND, service.deletePantheon(1));
		Mockito.verify(repo).deletePantheon(1);
	}

}
