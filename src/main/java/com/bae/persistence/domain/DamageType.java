package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DamageType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "damagetype_id")
	private int id;

	@Column(length = 8, name = "damagetype_name")
	private String name;

	public DamageType() {

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

		public DamageType build() {
			DamageType damageType = new DamageType();
			damageType.id = id;
			damageType.name = name;
			return damageType;
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
