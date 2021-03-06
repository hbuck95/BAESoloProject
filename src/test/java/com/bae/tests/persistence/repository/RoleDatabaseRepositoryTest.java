package com.bae.tests.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Role;
import com.bae.persistence.repository.RoleDatabaseRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class RoleDatabaseRepositoryTest {

	@InjectMocks
	private RoleDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, Role> roleMap;
	private static final String MOCK_ROLE_ARRAY = "[{\"id\":1,\"name\":\"Guardian\"}]";
	private static final String MOCK_ROLE_OBJECT = "{\"id\":1,\"name\":\"Guardian\"}";
	private Role roleB;

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		roleMap = new HashMap<>();
		roleMap.put(1, new Role.Builder().id(1).name("Guardian").build());
		roleB = new Role.Builder().id(5).name("Assassin").build();
	}

	@Test
	public void testGetAllRoles() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<Role>(roleMap.values()));
		assertEquals(MOCK_ROLE_ARRAY, repo.getAllRoles());
	}

	@Test
	public void testFindRoleWhichDoesExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		assertEquals(MOCK_ROLE_OBJECT, repo.findRole(1));
	}

	@Test
	public void testFindRoleWhichDoesNotExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		String reply = repo.findRole(2);
		assertEquals(Constants.ROLE_NOT_FOUND, reply);
	}

	@Test
	public void testDeleteRoleWhichDoesExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));

		// Check the result of deleting a role which does exist
		entityManager.remove(roleMap.get(1));
		String reply = repo.deleteRole(1);

		assertEquals(Constants.DELETE_ROLE_SUCCESS, reply);
	}

	@Test
	public void testDeleteRoleWhichDoesNotExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));

		// Check the result of deleting a role which doesn't exist
		String reply = repo.deleteRole(2);
		assertEquals(Constants.ROLE_NOT_FOUND, reply);

	}

	@Test
	public void testCreateRole() {
		String reply = repo.createRole(MOCK_ROLE_OBJECT);
		assertEquals(Constants.CREATE_ROLE_SUCCESS, reply);
	}

	@Test
	public void testUpdateRoleWhichDoesExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		Mockito.when(entityManager.merge(roleB)).thenReturn(roleB);

		String reply = repo.updateRole(1, util.getJSONForObject(roleB));
		Mockito.verify(entityManager, Mockito.times(1)).merge(roleMap.get(1));

		Role roleFromManager = entityManager.find(Role.class, 1);

		assertEquals(roleB.getName(), roleFromManager.getName());
		assertEquals(Constants.UPDATE_ROLE_SUCCESS, reply);
	}

	@Test
	public void testUpdateRoleWhichDoesNotExist() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		Mockito.when(entityManager.merge(roleB)).thenReturn(roleB);

		String reply = repo.updateRole(2, util.getJSONForObject(roleB));
		assertEquals(Constants.ROLE_NOT_FOUND, reply);
	}

	@Test
	public void testCheckRoleExists() {
		boolean res;
		res = repo.checkRoleExists(1);
		assertEquals(false, res);
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		res = repo.checkRoleExists(1);
		assertEquals(true, res);
	}

}
