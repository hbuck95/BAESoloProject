package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.DamageTypeService;

@Path("/damagetype")
public class DamageTypeController {

	@Inject
	private DamageTypeService service;

	public void setService(DamageTypeService service) {
		this.service = service;
	}

}
