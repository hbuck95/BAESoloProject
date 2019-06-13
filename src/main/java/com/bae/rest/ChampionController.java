package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.service.ChampionService;

@Path("/champion")
public class ChampionController {

	@Inject
	private ChampionService service;

	@Path("/getAllChampions")
	@GET
	@Produces({ "application/json" })
	public String getAllChampions() {
		return service.getAllChampions();
	}

	@Path("/getChampion/{championid}")
	@GET
	@Produces({ "application/json" })
	String findChampion(@PathParam("championid") int id) {
		return service.findChampion(id);
	}

	public void setService(ChampionService service) {
		this.service = service;
	}

}
