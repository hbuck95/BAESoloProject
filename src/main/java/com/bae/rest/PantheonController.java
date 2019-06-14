package com.bae.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.bae.business.service.PantheonService;

@Path("/pantheon")
public class PantheonController {

	@Inject
	private PantheonService service;

	public void setService(PantheonService service) {
		this.service = service;
	}

}
