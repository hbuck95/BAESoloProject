package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bae.business.service.ChampionGameModeStatsService;

@Path("/stats")
public class ChampionGameModeStatsController {

	@Inject
	private ChampionGameModeStatsService service;

	@Path("/getAllStats")
	@GET
	@Produces({ "application/json" })
	public String getAllChampionGameModeStats() {
		return service.getAllChampionGameModeStats();
	}

	public void setService(ChampionGameModeStatsService service) {
		this.service = service;
	}

}
