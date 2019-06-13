package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	@Path("/createChampion")
	@POST
	@Produces({ "application/json" })
	String createChampion(String champion) {
		return service.createChampion(champion);
	}

	@Path("/deleteChampion/{championid}")
	@DELETE
	@Produces({ "application/json" })
	String deleteChampion(@PathParam("championid") int id) {
		return service.deleteChampion(id);
	}

	@Path("/updateAccount/{championid}")
	@PUT
	@Produces({ "application/json" })
	String updateChampion(@PathParam("championid") int id, String champion) {
		return service.updateChampion(id, champion);
	}

	public void setService(ChampionService service) {
		this.service = service;
	}

}
