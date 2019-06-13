package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.ChampionService;

@Path("/champion")
public class ChampionController {

	@Inject
	private ChampionService service;

	public void setService(ChampionService service) {
		this.service = service;
	}

}
