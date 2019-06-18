package com.bae.tests.business.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.RoleServiceImpl;
import com.bae.persistence.repository.RoleDatabaseRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

	private static final String MOCK_ROLE_ARRAY = "[{\"id\":1,\"name\":\"Guardian\"}]";
	private static final String MOCK_ROLE_OBJECT = "{\"id\":1,\"name\":\"Guardian\"}";

	@InjectMocks
	private RoleServiceImpl service;

	@Mock
	private RoleDatabaseRepository repo;

	@Test
	public void testGetAllRoles() {
		Mockito.when(service.getAllRoles()).thenReturn(MOCK_ROLE_ARRAY);
		assertEquals(MOCK_ROLE_ARRAY, service.getAllRoles());
	}

	@Test
	public void testFindRoleExists() {
		Mockito.when(service.findRole(1)).thenReturn(MOCK_ROLE_OBJECT);
		assertEquals(MOCK_ROLE_OBJECT, service.findRole(1));
	}

	@Test
	public void testFindRoleDoesNotExist() {
		Mockito.when(service.findRole(1)).thenReturn(Constants.ROLE_NOT_FOUND);
		assertEquals(Constants.ROLE_NOT_FOUND, service.findRole(1));
		Mockito.verify(repo).findRole(1);
	}

	@Test
	public void testCreateRole() {
		Mockito.when(service.createRole(MOCK_ROLE_OBJECT)).thenReturn(Constants.CREATE_ROLE_SUCCESS);
		assertEquals(Constants.CREATE_ROLE_SUCCESS, service.createRole(MOCK_ROLE_OBJECT));
		Mockito.verify(repo).createRole(MOCK_ROLE_OBJECT);
	}

	@Test
	public void testDeleteRoleDoesExist() {
		Mockito.when(service.deleteRole(1)).thenReturn(Constants.DELETE_ROLE_SUCCESS);
		assertEquals(Constants.DELETE_ROLE_SUCCESS, service.deleteRole(1));
		Mockito.verify(repo).deleteRole(1);
	}

	@Test
	public void testDeleteRoleDoesNotExist() {
		Mockito.when(service.deleteRole(1)).thenReturn(Constants.ROLE_NOT_FOUND);
		assertEquals(Constants.ROLE_NOT_FOUND, service.deleteRole(1));
		Mockito.verify(repo).deleteRole(1);
	}

}
