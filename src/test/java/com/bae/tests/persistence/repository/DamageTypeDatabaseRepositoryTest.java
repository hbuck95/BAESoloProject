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

import com.bae.persistence.domain.DamageType;
import com.bae.persistence.repository.DamageTypeDatabaseRepository;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class DamageTypeDatabaseRepositoryTest {

	@InjectMocks
	private DamageTypeDatabaseRepository repo;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil util;
	private Map<Integer, DamageType> damageTypeMap;
	private static final String MOCK_DAMAGETYPE_ARRAY = "[{\"id\":1,\"name\":\"Magical\"}]";
	private static final String MOCK_DAMAGETYPE_OBJECT = "{\"id\":1,\"name\":\"Magical\"}";

	@Before
	public void setup() {
		repo.setEntityManager(entityManager);
		util = new JSONUtil();
		repo.setUtil(util);
		damageTypeMap = new HashMap<>();
		damageTypeMap.put(1, new DamageType.Builder().id(1).name("Magical").build());
	}

	@Test
	public void testGetAllDamageTypes() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<DamageType>(damageTypeMap.values()));
		assertEquals(MOCK_DAMAGETYPE_ARRAY, repo.getAllDamageTypes());
	}

	@Test
	public void testFindDamageType() {
		Mockito.when(entityManager.find(DamageType.class, 1)).thenReturn(damageTypeMap.get(1));
		assertEquals(MOCK_DAMAGETYPE_OBJECT, repo.findDamageType(1));
	}

}
