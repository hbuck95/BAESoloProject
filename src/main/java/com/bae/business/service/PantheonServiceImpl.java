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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePantheon(int id, String pantheon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPantheon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
