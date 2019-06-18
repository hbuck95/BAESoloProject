package com.bae.tests.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.service.RoleService;
import com.bae.rest.RoleController;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

	private static final String MOCK_ROLE_ARRAY = "[{\"id\":1,\"name\":\"Guardian\"}]";
	private static final String MOCK_ROLE_OBJECT = "{\"id\":1,\"name\":\"Guardian\"}";

	@InjectMocks
	private RoleController controller;

	@Mock
	private RoleService service;

	@Before
	public void setup() {
		controller.setService(service);
	}

	@Test
	public void testGetAllRoles() {
		Mockito.when(service.getAllRoles()).thenReturn(MOCK_ROLE_ARRAY);
		assertEquals(MOCK_ROLE_ARRAY, controller.getAllRoles());
	}

	@Test
	public void testFindRoleExists() {
		Mockito.when(service.findRole(1)).thenReturn(MOCK_ROLE_OBJECT);
		assertEquals(MOCK_ROLE_OBJECT, controller.findRole(1));
	}

	@Test
	public void testFindRoleDoesNotExist() {
		Mockito.when(service.findRole(1)).thenReturn(Constants.ROLE_NOT_FOUND);
		assertEquals(Constants.ROLE_NOT_FOUND, controller.findRole(1));
		Mockito.verify(service).findRole(1);
	}

	@Test
	public void testCreateRole() {
		Mockito.when(service.createRole(MOCK_ROLE_OBJECT)).thenReturn(Constants.CREATE_ROLE_SUCCESS);
		assertEquals(Constants.CREATE_ROLE_SUCCESS, controller.createRole(MOCK_ROLE_OBJECT));
		Mockito.verify(service).createRole(MOCK_ROLE_OBJECT);
	}

	@Test
	public void testDeleteRoleDoesExist() {
		Mockito.when(service.deleteRole(1)).thenReturn(Constants.DELETE_ROLE_SUCCESS);
		assertEquals(Constants.DELETE_ROLE_SUCCESS, controller.deleteRole(1));
		Mockito.verify(service).deleteRole(1);
	}

	@Test
	public void testDeleteRoleDoesNotExist() {
		Mockito.when(service.deleteRole(1)).thenReturn(Constants.ROLE_NOT_FOUND);
		assertEquals(Constants.ROLE_NOT_FOUND, controller.deleteRole(1));
		Mockito.verify(service).deleteRole(1);
	}

	@Test
	public void testUpdateRoleDoesExist() {
		Mockito.when(service.updateRole(1, MOCK_ROLE_OBJECT)).thenReturn(Constants.UPDATE_ROLE_SUCCESS);
		assertEquals(Constants.UPDATE_ROLE_SUCCESS, controller.updateRole(1, MOCK_ROLE_OBJECT));
		Mockito.verify(service).updateRole(1, MOCK_ROLE_OBJECT);
	}

	@Test
	public void testUpdateRoleDoesNotExist() {
		Mockito.when(service.updateRole(1, MOCK_ROLE_OBJECT)).thenReturn(Constants.ROLE_NOT_FOUND);
		assertEquals(Constants.ROLE_NOT_FOUND, controller.updateRole(1, MOCK_ROLE_OBJECT));
		Mockito.verify(service).updateRole(1, MOCK_ROLE_OBJECT);
	}

}
