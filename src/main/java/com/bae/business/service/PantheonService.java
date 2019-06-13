package com.bae.business.service;

public interface PantheonService {

	String getAllPantheons();

	String createPantheon(String pantheon);

	String deletePantheon(int id);

	String updatePantheon(int id, String pantheon);

	String findPantheon(int id);
}
