package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bae.business.service.RoleService;

@Path("/role")
public class RoleController {

	@Inject
	private RoleService service;

	@Path("/getAllRoles")
	@GET
	@Produces({ "application/json" })
	public String getAllRoles() {
		return service.getAllRoles();
	}

	public void setService(RoleService service) {
		this.service = service;
	}

}
