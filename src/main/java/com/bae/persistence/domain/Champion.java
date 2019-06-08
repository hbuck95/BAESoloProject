package com.bae.persistence.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Champion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "champion_id")
	private int id;

	@Column(length = 15, name = "champion_name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pantheon_id")
	private Pantheon pantheon;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "damagetype_id")
	private DamageType damageType;

	@Column(length = 3)
	private int health;

	@Column(length = 2)
	private int damage;

	// Default constructor for JSONUtil to implement
	public Champion() {

	}

	// Builder class for creating Character objects for testing
	public static class Builder {
		private int id;
		private String name;
		private Role role;
		private Pantheon pantheon;
		private DamageType damageType;
		private int health;
		private int damage;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder role(Role role) {
			this.role = role;
			return this;
		}

		public Builder pantheon(Pantheon pantheon) {
			this.pantheon = pantheon;
			return this;
		}

		public Builder damageType(DamageType damageType) {
			this.damageType = damageType;
			return this;
		}

		public Builder health(int health) {
			this.health = health;
			return this;
		}

		public Builder damage(int damage) {
			this.damage = damage;
			return this;
		}

		public Champion build() {
			Champion champion = new Champion();
			champion.id = this.id;
			champion.name = this.name;
			champion.role = this.role;
			champion.pantheon = this.pantheon;
			champion.damageType = this.damageType;
			champion.health = this.health;
			champion.damage = this.damage;
			return champion;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the pantheonId
	 */
	public Pantheon getPantheon() {
		return pantheon;
	}

	/**
	 * @param pantheon the pantheonId to set
	 */
	public void setPantheon(Pantheon pantheon) {
		this.pantheon = pantheon;
	}

	/**
	 * @return the damageType
	 */
	public DamageType getDamageType() {
		return damageType;
	}

	/**
	 * @param damageType the damageType to set
	 */
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
