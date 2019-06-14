package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.service.DamageTypeService;

@Path("/damagetype")
public class DamageTypeController {

	@Inject
	private DamageTypeService service;

	@Path("/getAllDamageTypes")
	@GET
	@Produces({ "application/json" })
	public String getAllChampions() {
		return service.getAllDamageTypes();
	}

	@Path("/getDamageType/{damageType}")
	@GET
	@Produces({ "application/json" })
	public String findDamageType(@PathParam("damageType") int id) {
		return service.findDamageType(id);
	}

	public void setService(DamageTypeService service) {
		this.service = service;
	}

}
