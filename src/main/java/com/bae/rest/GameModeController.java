package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.GameModeService;

@Path("/gamemode")
public class GameModeController {

	@Inject
	private GameModeService service;

	public void setService(GameModeService service) {
		this.service = service;
	}

}
