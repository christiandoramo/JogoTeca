package br.jogoteca.system.models;

import java.util.ArrayList;
import br.jogoteca.system.models.Game;


public class GameItem {
	private int ID;
	private double totalValue;
	private ArrayList<Game> games;

	
	public double calculateTotalValue(ArrayList<Game> listToCalculate){
		double result = 0;
		for (Game valor : listToCalculate){
			result+=valor.getPrice();
		}
			return result;
	}
	public ArrayList<Game> getGames(){
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games.clear();
		this.games.addAll(games);
	}
	
	public double gettotalValue(){
		return totalValue;
	}
	public void settotalValue(double totalValue){
		this.totalValue = totalValue;
	}
	public int getId(){
		return ID;
	}
	public void setId(int ID){
		this.ID = ID;
	}
	
	
	public GameItem (int ID, double totalValue, ArrayList<Game> games){		
		this.ID = ID;
		this.totalValue = totalValue;
		this.games = games;
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