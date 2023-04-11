package com.example.jogotecaintellij.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.Game;
import com.example.jogotecaintellij.model.ItemJogo;

public class itemJogoController {

	private ArrayList<ItemJogo> gameItemList;

	private static itemJogoController instance;

	public itemJogoController() {
		this.gameItemList = new ArrayList<ItemJogo>();
		//////////////////////////////////////////////////////////////
		this.gameItemRepository = new GenericRepository<>("itemJogos.dat");
		//////////////////////////////////////////////////////////////
	}

	public ArrayList<ItemJogo> showGameItemList() {
		return this.gameItemList;
	}

	public void addGameItem(ItemJogo gameItem) {
		gameItemList.add(gameItem);
	}

	public void attGameItem(ItemJogo gameItem1, ItemJogo gameItem2) {
		gameItemList.remove(gameItem1);
		gameItemList.add(gameItem2);
	}

	public void removeGameItem(ItemJogo gameItem) {
		gameItemList.remove(gameItem);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private GenericRepository<ItemJogo> gameItemRepository;

	public static itemJogoController getInstance() {
		if (instance == null) {
			instance = new itemJogoController();
		}
		return instance;
	}

	public void insertGameItem(double value, Game game) throws ElementAlreadyExistsException {
		ItemJogo gameItem = new ItemJogo( value, game);
		gameItemRepository.insert(gameItem);
	}

	public ItemJogo searchGameItemById(int id) throws ElementDoesNotExistException {
		return gameItemRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public List<ItemJogo> searchAllGameItem() throws ElementsDoNotExistException {
		return gameItemRepository.read();
	}
	
	public void destroyAllGameItem() throws ElementsDoNotExistException, ElementDoesNotExistException {
		for (ItemJogo item : gameItemRepository.read())
			gameItemRepository.delete(item);
	}

	public void destroyGameItemById(int id) throws ElementDoesNotExistException{
		ItemJogo item = gameItemRepository.read().stream().filter(x->x.getId()==id).findFirst().orElse(null);
		gameItemRepository.delete(item);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
}