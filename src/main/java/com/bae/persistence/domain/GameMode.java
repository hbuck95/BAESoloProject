package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameMode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gamemode_id")
	private int id;

	@Column(length = 8, name = "gamemode_name")
	private String name;

	// Default constructor for JSONUtil to implement
	public GameMode() {

	}

	public static class Builder {
		private int id;
		private String name;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public GameMode build() {
			GameMode gameMode = new GameMode();
			gameMode.id = id;
			gameMode.name = name;
			return gameMode;
		}

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
