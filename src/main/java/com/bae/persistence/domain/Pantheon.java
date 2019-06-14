package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pantheon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pantheon_id")
	private int id;

	@Column(length = 10, name = "pantheon_name")
	private String name;

	// Default constructor for JSONUtil to implement
	public Pantheon() {

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

		public Pantheon build() {
			Pantheon pantheon = new Pantheon();
			pantheon.id = id;
			pantheon.name = name;
			return pantheon;
		}

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
