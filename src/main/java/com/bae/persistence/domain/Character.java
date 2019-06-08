package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Character {

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
	public Character() {

	}

	// Builder class for creating Character objects for testing
	public static class CharacterBuilder {
		private int id;
		private String name;
		private int pantheonId;
		private int damageType;
		private int health;
		private int damage;

		private CharacterBuilder() {

		}

		public CharacterBuilder id(int id) {
			this.id = id;
			return this;
		}

		public CharacterBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CharacterBuilder pantheon(int pantheonId) {
			this.pantheonId = pantheonId;
			return this;
		}

		public CharacterBuilder damageType(int damageType) {
			this.damageType = damageType;
			return this;
		}

		public CharacterBuilder health(int health) {
			this.health = health;
			return this;
		}

		public CharacterBuilder damage(int damage) {
			this.damage = damage;
			return this;
		}

		public Character build() {
			Character character = new Character();
			character.id = this.id;
			character.name = this.name;
			character.pantheonId = this.pantheonId;
			character.damageType = this.damageType;
			character.health = this.health;
			character.damage = this.damage;
			return character;
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
