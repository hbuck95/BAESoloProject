package com.bae.persistence.repository;

public interface DamageTypeRepository {
	String getAllDamageTypes();

	String createDamageType(String damageType);

	String deleteDamageType(int id);

	String updateDamageType(int id, String damageType);

	String findDamageType(int id);
}
