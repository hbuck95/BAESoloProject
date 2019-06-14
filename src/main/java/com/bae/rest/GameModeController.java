package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.service.GameModeService;

@Path("/gamemode")
public class GameModeController {

	@Inject
	private GameModeService service;

	@Path("/getAllGameModes")
	@GET
	@Produces({ "application/json" })
	public String getAllGameModes() {
		return service.getAllGameModes();
	}

	@Path("/getGameMode/{gameModeId}")
	@GET
	@Produces({ "application/json" })
	public String getGameMode(@PathParam("gameModeId") int id) {
		return service.findGameMode(id);
	}

	public void setService(GameModeService service) {
		this.service = service;
	}

}
