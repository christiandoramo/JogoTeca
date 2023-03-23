package br.jogoteca.system.controllers;

import java.util.ArrayList;

import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.models.GameItem;


public class GameItemControllers {

    private ArrayList<GameItem> gameItemList;
    
    private static GameItemControllers instance;
    
    public GameItemControllers(){
        this.gameItemList = new ArrayList<GameItem>();
    }

    public ArrayList<GameItem> showGameItemList() {    
    	return this.gameItemList;
	}

    public void addGameItem(GameItem gameItem){   		
    	gameItemList.add(gameItem);
    }
    public void attGameItem(GameItem gameItem1, GameItem gameItem2) {
        gameItemList.remove(gameItem1);
        gameItemList.add(gameItem2);
    }

    public void removeGameItem(GameItem gameItem){
        gameItemList.remove(gameItem);
    }

    public static GameItemControllers getInstance() {
		if (instance == null) {
			instance = new GameItemControllers();
		}
		return instance;
	}
}