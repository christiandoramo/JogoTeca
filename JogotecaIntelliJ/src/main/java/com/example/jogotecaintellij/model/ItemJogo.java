package com.example.jogotecaintellij.model;


import java.io.Serializable;
import java.util.ArrayList;

public class ItemJogo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double value;
	private ArrayList<Game> games;
	//////////////////////////////////////////////////////////////////////////////////
	private Game game;
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ItemJogo( double value, Game game) {
		super();
		this.value = value;
		this.game = game;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////

	public double calculateValue(ArrayList<Game> listToCalculate) {
		double result = 0;
		for (Game valor : listToCalculate) {
			result += valor.getPrice();
		}
		return result;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games.clear();
		this.games.addAll(games);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemJogo(int id, double value, ArrayList<Game> games) {
		this.id = id;
		this.value = value;
		this.games = games;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemJogo other = (ItemJogo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}