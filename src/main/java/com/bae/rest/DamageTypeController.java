package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	@Path("/createDamageType")
	@POST
	@Produces({ "application/json" })
	public String createDamageType(String damageType) {
		return service.createDamageType(damageType);
	}

	@Path("/deleteDamageType/{damageType}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteDamageType(@PathParam("damageType") int id) {
		return service.deleteDamageType(id);
	}

	@Path("/updateDamageType/{damageType}")
	@PUT
	@Produces({ "application/json" })
	public String updateDamageType(@PathParam("damageType") int id, String damageType) {
		return service.updateDamageType(id, damageType);
	}

	public void setService(DamageTypeService service) {
		this.service = service;
	}

}
