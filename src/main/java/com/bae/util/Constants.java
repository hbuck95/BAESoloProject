package com.bae.util;

public final class Constants {

	// Declare private default constructor
	// This class will be only accessible via an imported static context
	private Constants() {

	}

	public static final String CREATE_CHAMPION_SUCCESS = "{\"message\": \"Champion successfully created!\"}";
	public static final String DELETE_CHAMPION_SUCCESS = "{\"message\": \"The specified champion has successfully been deleted\"}";
	public static final String UPDATE_CHAMPION_SUCCESS = "{\"message\": \"The specified champion has successfuly been updated\"}";
	public static final String CHAMPION_NOT_FOUND = "{\"message\": \"A champion with the specified ID could not be found\"}";

}
