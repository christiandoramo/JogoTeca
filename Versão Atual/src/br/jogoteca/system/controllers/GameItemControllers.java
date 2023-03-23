package br.jogoteca.system.controllers;


import java.util.List;
import java.util.ArrayList;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;

public class GameItemControllers {

    private ArrayList<GameItem> gameItemList;
    
    private static GameItemControllers instance;
    
    public GameItemControllers(){
        this.gameItemList = new ArrayList<>();
    }

    public ArrayList<GameItem> showGameItemList() {    
    	return this.gameItemList;
	}

    public void addGameItem(GameItem gameItem) throws ElementAlreadyExistsException{
        gameItemList.add(gameItem);
    }

    public void attGameItem(GameItem gameItem1, GameItem gameItem2) throws Exception{
        gameItemList.remove(gameItem1);
        gameItemList.add(gameItem2);
    }

    public void removeGameItem(GameItem gameItem) throws ElementDoesNotExistException{
        gameItemList.remove(gameItem);
    }

    public static GameItemControllers getInstance() {
		if (instance == null) {
			instance = new GameItemControllers();
		}
		return instance;
	}
}