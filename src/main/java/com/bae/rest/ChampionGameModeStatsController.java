package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@Path("/getStats/{id}")
	@GET
	@Produces({ "application/json" })
	public String findChampionGameModeStats(@PathParam("id") int id) {
		return service.findChampionGameModeStats(id);
	}

	@Path("/createStats")
	@POST
	@Produces({ "application/json" })
	public String createChampionGameModeStats(String stats) {
		return service.createChampionGameModeStats(stats);
	}

	@Path("/deleteStats/{statsId}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteChampionGameModeStats(@PathParam("statsId") int id) {
		return service.deleteChampionGameModeStats(id);
	}

	@Path("/updateStats/{statsId}")
	@PUT
	@Produces({ "application/json" })
	public String updateChampionGameModeStats(@PathParam("statsId") int id, String gameMode) {
		return service.updateChampionGameModeStats(id, gameMode);
	}

	public void setService(ChampionGameModeStatsService service) {
		this.service = service;
	}

}
