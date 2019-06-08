package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Champion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 15)
	private String name;

	// TODO: Update with a @ManyToOne with the pantheon entity when implemented
	private int pantheonId;

	// TODO: Update with a @ManyToOne with the damagetype entity when implemented
	private int damageType;

	@Column(length = 3)
	private int health;

	@Column(length = 2)
	private int damage;

	// Default constructor for JSONUtil to implement
	public Champion() {

	}

	// Builder class for creating Character objects for testing
	public static class ChampionBuilder {
		private int id;
		private String name;
		private int pantheonId;
		private int damageType;
		private int health;
		private int damage;

		private ChampionBuilder() {

		}

		public ChampionBuilder id(int id) {
			this.id = id;
			return this;
		}

		public ChampionBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ChampionBuilder pantheon(int pantheonId) {
			this.pantheonId = pantheonId;
			return this;
		}

		public ChampionBuilder damageType(int damageType) {
			this.damageType = damageType;
			return this;
		}

		public ChampionBuilder health(int health) {
			this.health = health;
			return this;
		}

		public ChampionBuilder damage(int damage) {
			this.damage = damage;
			return this;
		}

		public Champion build() {
			Champion champion = new Champion();
			champion.id = this.id;
			champion.name = this.name;
			champion.pantheonId = this.pantheonId;
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
	 * @return the pantheonId
	 */
	public int getPantheonId() {
		return pantheonId;
	}

	/**
	 * @param pantheonId the pantheonId to set
	 */
	public void setPantheonId(int pantheonId) {
		this.pantheonId = pantheonId;
	}

	/**
	 * @return the damageType
	 */
	public int getDamageType() {
		return damageType;
	}

	/**
	 * @param damageType the damageType to set
	 */
	public void setDamageType(int damageType) {
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
