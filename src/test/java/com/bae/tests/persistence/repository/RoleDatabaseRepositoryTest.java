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

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		roleMap = new HashMap<>();
		roleMap.put(1, new Role.Builder().id(1).name("Guardian").build());
	}

	@Test
	public void testGetAllRoles() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<Role>(roleMap.values()));
		assertEquals(MOCK_ROLE_ARRAY, repo.getAllRoles());
	}

	@Test
	public void testFindRole() {
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleMap.get(1));
		assertEquals(MOCK_ROLE_OBJECT, repo.findRole(1));
	}

	@Test
	public void testDeleteRole() {
		Role roleA = roleMap.get(1);
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roleA);
		entityManager.remove(roleA);
		Mockito.verify(entityManager, Mockito.times(1)).remove(roleA);
	}

	@Test
	public void testCreateRole() {
		String reply = repo.createRole(MOCK_ROLE_OBJECT);
		assertEquals(Constants.CREATE_ROLE_SUCCESS, reply);
	}

}
