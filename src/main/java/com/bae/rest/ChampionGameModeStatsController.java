package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.ChampionGameModeStatsService;

@Path("/stats")
public class ChampionGameModeStatsController {

	@Inject
	private ChampionGameModeStatsService service;

	public void setService(ChampionGameModeStatsService service) {
		this.service = service;
	}

}
