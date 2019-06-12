package com.bae.persistence.repository;

public interface PantheonRepository {

	String getAllPantheons();

	String createPantheon(String pantheon);

	String deletePantheon(int id);

	String updatePantheon(int id, String pantheon);

	String findPantheon(int id);
}
