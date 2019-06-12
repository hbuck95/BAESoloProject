package com.bae.business.service;

public interface DamageTypeService {
	String getAllDamageTypes();

	String createDamageType(String damageType);

	String deleteDamageType(int id);

	String updateDamageType(int id, String damageType);

	String findDamageType(int id);
}
