package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bae.business.service.PantheonService;

@Path("/pantheon")
public class PantheonController {

	@Inject
	private PantheonService service;

	@Path("/getAllPantheons")
	@GET
	@Produces({ "application/json" })
	public String getAllRoles() {
		return service.getAllPantheons();
	}

	public void setService(PantheonService service) {
		this.service = service;
	}

}
