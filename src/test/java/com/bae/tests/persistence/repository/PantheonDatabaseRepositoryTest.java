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

import com.bae.persistence.domain.Pantheon;
import com.bae.persistence.repository.PantheonDatabaseRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class PantheonDatabaseRepositoryTest {
	@InjectMocks
	private PantheonDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, Pantheon> pantheonMap;
	private static final String MOCK_PANTHEON_ARRAY = "[{\"id\":1,\"name\":\"Norse\"}]";
	private static final String MOCK_PANTHEON_OBJECT = "{\"id\":1,\"name\":\"Norse\"}";
	private Pantheon pantheonB;

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		pantheonMap = new HashMap<>();
		pantheonMap.put(1, new Pantheon.Builder().id(1).name("Norse").build());
		pantheonB = new Pantheon.Builder().id(5).name("Egyptian").build();
	}

	@Test
	public void testGetAllPantheons() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<Pantheon>(pantheonMap.values()));
		assertEquals(MOCK_PANTHEON_ARRAY, repo.getAllPantheons());
	}

	@Test
	public void testFindPantheonWhichDoesExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));
		assertEquals(MOCK_PANTHEON_OBJECT, repo.findPantheon(1));
	}

	@Test
	public void testFindPantheonWhichDoesNotExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));
		String reply = repo.findPantheon(2);
		assertEquals(Constants.PANTHEON_NOT_FOUND, reply);
	}

	@Test
	public void testDeletePantheonWhichDoesExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));

		// Check the result of deleting a pantheon which does exist
		entityManager.remove(pantheonMap.get(1));
		String reply = repo.deletePantheon(1);

		assertEquals(Constants.DELETE_PANTHEON_SUCCESS, reply);
	}

	@Test
	public void testDeletePantheonWhichDoesNotExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));

		// Check the result of deleting a role which doesn't exist
		String reply = repo.deletePantheon(2);
		assertEquals(Constants.PANTHEON_NOT_FOUND, reply);
	}

	@Test
	public void testCreatePantheon() {
		String reply = repo.createPantheon(MOCK_PANTHEON_OBJECT);
		assertEquals(Constants.CREATE_PANTHEON_SUCCESS, reply);
	}

	@Test
	public void testUpdatePantheonWhichDoesExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));
		Mockito.when(entityManager.merge(pantheonB)).thenReturn(pantheonB);

		String reply = repo.updatePantheon(1, util.getJSONForObject(pantheonB));
		Mockito.verify(entityManager, Mockito.times(1)).merge(pantheonMap.get(1));

		Pantheon pantheonFromManager = entityManager.find(Pantheon.class, 1);

		assertEquals(pantheonB.getName(), pantheonFromManager.getName());
		assertEquals(Constants.UPDATE_PANTHEON_SUCCESS, reply);
	}

	@Test
	public void testUpdatePantheonWhichDoesNotExist() {
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));
		Mockito.when(entityManager.merge(pantheonB)).thenReturn(pantheonB);

		String reply = repo.updatePantheon(2, util.getJSONForObject(pantheonB));
		assertEquals(Constants.PANTHEON_NOT_FOUND, reply);
	}

	@Test
	public void testCheckPantheonExists() {
		boolean res;
		res = repo.checkPantheonExists(1);
		assertEquals(false, res);
		Mockito.when(entityManager.find(Pantheon.class, 1)).thenReturn(pantheonMap.get(1));
		res = repo.checkPantheonExists(1);
		assertEquals(true, res);
	}

}
