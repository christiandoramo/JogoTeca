package br.jogoteca.system.models;

import java.util.List;

public class GameItem {
	private int ID;
	private double value;
	private List<Game> games;

	public List<Game> getGames(){
		return games;
	}

	public void addGames(Game gameToAdd) {
		this.games.add(gameToAdd);
	}
	public double setValue(){
		return value;
	}
	public double getValue(){
		return value;
	}
	public int getId(){
		return ID;
	}
	public void setId(int ID){
		this.ID = ID;
	}
	public GameItem (int ID, double value, List<Game> games){
		this.ID = ID;
		this.value = value;
		this.games = games;
	}
	
	public double calculateTotalValue(List<Game> listToCalculate){
		double result = 0;
		for (Game valor : listToCalculate){
			result+=valor.getPrice();
		}
			return result;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		GameItem other = (GameItem) obj;
		if (ID != other.ID)
			return false;
		return true;
	}	
}