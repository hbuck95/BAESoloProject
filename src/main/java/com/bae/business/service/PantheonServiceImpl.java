package com.bae.business.service;

import javax.inject.Inject;

import com.bae.persistence.repository.PantheonRepository;

public class PantheonServiceImpl implements PantheonService {

	@Inject
	private PantheonRepository repo;

	@Override
	public String getAllPantheons() {
		return repo.getAllPantheons();
	}

	@Override
	public String createPantheon(String pantheon) {
		return repo.createPantheon(pantheon);
	}

	@Override
	public String deletePantheon(int id) {
		return repo.deletePantheon(id);
	}

	@Override
	public String updatePantheon(int id, String pantheon) {
		return repo.updatePantheon(id, pantheon);
	}

	@Override
	public String findPantheon(int id) {
		return repo.findPantheon(id);
	}

}
