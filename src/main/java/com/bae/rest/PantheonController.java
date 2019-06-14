package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.service.PantheonService;

@Path("/pantheon")
public class PantheonController {

	@Inject
	private PantheonService service;

	@Path("/getAllPantheons")
	@GET
	@Produces({ "application/json" })
	public String getAllPantheons() {
		return service.getAllPantheons();
	}

	@Path("/createPantheon")
	@POST
	@Produces({ "application/json" })
	public String createPantheon(String pantheon) {
		return service.createPantheon(pantheon);
	}

	@Path("/deletePantheon/{pantheonId}")
	@DELETE
	@Produces({ "application/json" })
	public String deletePantheon(@PathParam("pantheonId") int id) {
		return service.deletePantheon(id);
	}

	public void setService(PantheonService service) {
		this.service = service;
	}

}
