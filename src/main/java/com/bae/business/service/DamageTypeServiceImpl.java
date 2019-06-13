package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.DamageTypeRepository;

public class DamageTypeServiceImpl implements DamageTypeService {

	@Inject
	private DamageTypeRepository repo;

	@Override
	public String getAllDamageTypes() {
		return repo.getAllDamageTypes();
	}

	@Override
	public String createDamageType(String damageType) {
		return repo.createDamageType(damageType);
	}

	@Override
	public String deleteDamageType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateDamageType(int id, String damageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDamageType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
