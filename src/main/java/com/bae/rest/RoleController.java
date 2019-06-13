package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.RoleService;

@Path("/role")
public class RoleController {

	@Inject
	private RoleService service;

	public void setService(RoleService service) {
		this.service = service;
	}

}
