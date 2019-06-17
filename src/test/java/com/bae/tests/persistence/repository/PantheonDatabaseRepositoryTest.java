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

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		pantheonMap = new HashMap<>();
		pantheonMap.put(1, new Pantheon.Builder().id(1).name("Norse").build());
	}

	@Test
	public void testGetAllPantheons() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<Pantheon>(pantheonMap.values()));
		assertEquals(MOCK_PANTHEON_ARRAY, repo.getAllPantheons());
	}

}
