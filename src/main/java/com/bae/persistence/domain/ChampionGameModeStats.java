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
public class ChampionGameModeStats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "championgamemodestats_id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "champion_id")
	private Champion champion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gamemode_id")
	private GameMode gameMode;

	@Column(name = "winrate")
	private double winRate;

	@Column(name = "pickrate")
	private double pickRate;

	@Column(name = "banrate")
	private double banRate;

	// Default constructor for JSONUtil to implement
	public ChampionGameModeStats() {

	}

	public static class Builder {
		private int id;
		private Champion champion;
		private GameMode gameMode;
		private double winRate;
		private double pickRate;
		private double banRate;

		public Builder() {

		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder champion(Champion champion) {
			this.champion = champion;
			return this;
		}

		public Builder gameMode(GameMode gameMode) {
			this.gameMode = gameMode;
			return this;
		}

		public Builder winRate(double winRate) {
			this.winRate = winRate;
			return this;
		}

		public Builder pickRate(double pickRate) {
			this.pickRate = pickRate;
			return this;
		}

		public Builder banRate(double banRate) {
			this.banRate = banRate;
			return this;
		}

		public ChampionGameModeStats build() {
			ChampionGameModeStats cgms = new ChampionGameModeStats();
			cgms.id = this.id;
			cgms.champion = this.champion;
			cgms.gameMode = this.gameMode;
			cgms.winRate = this.winRate;
			cgms.pickRate = this.pickRate;
			cgms.banRate = this.banRate;
			return cgms;
		}

	}

	/**
	 * @return the champion
	 */
	public Champion getChampion() {
		return champion;
	}

	/**
	 * @param champion the champion to set
	 */
	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	/**
	 * @return the gameMode
	 */
	public GameMode getGameMode() {
		return gameMode;
	}

	/**
	 * @param gameMode the gameMode to set
	 */
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	/**
	 * @return the winRate
	 */
	public double getWinRate() {
		return winRate;
	}

	/**
	 * @param winRate the winRate to set
	 */
	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	/**
	 * @return the pickRate
	 */
	public double getPickRate() {
		return pickRate;
	}

	/**
	 * @param pickRate the pickRate to set
	 */
	public void setPickRate(double pickRate) {
		this.pickRate = pickRate;
	}

	/**
	 * @return the banRate
	 */
	public double getBanRate() {
		return banRate;
	}

	/**
	 * @param banRate the banRate to set
	 */
	public void setBanRate(double banRate) {
		this.banRate = banRate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
