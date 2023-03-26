package br.jogoteca.system.controllers;

import java.util.ArrayList;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.GameItem;

public class GameItemControllers {

	private ArrayList<GameItem> gameItemList;

	private static GameItemControllers instance;

	public GameItemControllers() {
		this.gameItemList = new ArrayList<GameItem>();
		//////////////////////////////////////////////////////////////
		this.gameItemRepository = new GenericRepository<>("gameItems.dat");
		lastId = gameItemRepository.read().size();
		//////////////////////////////////////////////////////////////
	}

	public ArrayList<GameItem> showGameItemList() {
		return this.gameItemList;
	}

	public void addGameItem(GameItem gameItem) {
		gameItemList.add(gameItem);
	}

	public void attGameItem(GameItem gameItem1, GameItem gameItem2) {
		gameItemList.remove(gameItem1);
		gameItemList.add(gameItem2);
	}

	public void removeGameItem(GameItem gameItem) {
		gameItemList.remove(gameItem);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private int lastId;
	private GenericRepository<GameItem> gameItemRepository;

	public static GameItemControllers getInstance() {
		if (instance == null) {
			instance = new GameItemControllers();
		}
		return instance;
	}

	public void insertGameItem(double value, Game game) throws ElementAlreadyExistsException {
		GameItem gameItem = new GameItem(lastId + 1, value, game);
		gameItemRepository.insert(gameItem);
		lastId++;
	}

	public GameItem searchGameItemById(int id) throws ElementDoesNotExistException {
		return gameItemRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public List<GameItem> searchAllGameItem() throws ElementsDoNotExistException {
		return gameItemRepository.read();
	}
	
	public void destroyAllGameItem() throws ElementsDoNotExistException, ElementDoesNotExistException {
		for (GameItem item : gameItemRepository.read())
			gameItemRepository.delete(item);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
}