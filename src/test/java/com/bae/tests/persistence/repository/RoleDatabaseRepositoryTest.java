package com.bae.tests.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

	private static final String MOCK_ROLE_ARRAY = "[{\"id\":1,\"name\":\"Guardian\"}]";
	private static final String MOCK_ROLE_OBJECT = "{\"id\":1,\"name\":\"Guardian\"}";

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllRoles() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Role> roles = new ArrayList<>();
		roles.add(new Role.Builder().id(1).name("Guardian").build());
		Mockito.when(query.getResultList()).thenReturn(roles);
		assertEquals(MOCK_ROLE_ARRAY, repo.getAllRoles());
	}

	@Test
	public void testFindRole() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role.Builder().id(1).name("Guardian").build());
		Mockito.when(entityManager.find(Role.class, 1)).thenReturn(roles.get(0));
		assertEquals(MOCK_ROLE_OBJECT, repo.findRole(1));
	}

}
