package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@Path("/getRole/{roleId}")
	@GET
	@Produces({ "application/json" })
	public String findRole(@PathParam("roleId") int roleId) {
		return service.findRole(roleId);
	}

	@Path("/createRole")
	@POST
	@Produces({ "application/json" })
	public String createRole(String role) {
		return service.createRole(role);
	}

	@Path("/deleteRole/{roleId}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteRole(@PathParam("roleId") int id) {
		return service.deleteRole(id);
	}

	@Path("/updateRole/{roleId}")
	@PUT
	@Produces({ "application/json" })
	public String updateRole(@PathParam("roleId") int id, String role) {
		return service.updateRole(id, role);
	}

	public void setService(RoleService service) {
		this.service = service;
	}

}
