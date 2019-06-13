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
		return repo.deleteDamageType(id);
	}

	@Override
	public String updateDamageType(int id, String damageType) {
		return repo.updateDamageType(id, damageType);
	}

	@Override
	public String findDamageType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
