package com.example.jogotecaintellij.controller;

import java.util.List;
import java.util.Optional;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.enums.StatusJogo;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.Jogo;
import com.example.jogotecaintellij.model.ItemJogo;

public class ItemJogoController {

    private static ItemJogoController instance;

    public ItemJogoController() {
        //////////////////////////////////////////////////////////////
        this.gameItemRepository = new GenericRepository<>("itemJogos.dat");
        //////////////////////////////////////////////////////////////
    }

    private GenericRepository<ItemJogo> gameItemRepository;

    public static ItemJogoController getInstance() {
        if (instance == null) {
            instance = new ItemJogoController();
        }
        return instance;
    }


    public void insertGameItem(Jogo game) throws ElementAlreadyExistsException {
        gameItemRepository.insert(new ItemJogo(game));
    }

    public void insertGameItem(double value, Jogo game) throws ElementAlreadyExistsException {
        ItemJogo gameItem = new ItemJogo(value, game);
        gameItemRepository.insert(gameItem);
    }

    public void updateGameItem(Jogo game) throws ElementAlreadyExistsException {
        Optional<ItemJogo> primeiroItemJogoDisponivel = gameItemRepository.read()
                .stream()
                .filter(i -> i.getStatus() == StatusJogo.DISPONIVEL
                        && i.getGame().getId() == game.getId())
                .findFirst();
        if (primeiroItemJogoDisponivel.isPresent()) {
            ItemJogo ultimoJogoIgual = primeiroItemJogoDisponivel.get();
            insertGameItem(game);
            try {
                gameItemRepository.update(ultimoJogoIgual);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Esse foi o primeiro " + game.getName() + " a ser registrado");
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

    public void destroyGameItemById(int id) throws ElementDoesNotExistException {
        ItemJogo item = gameItemRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        gameItemRepository.delete(item);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
}