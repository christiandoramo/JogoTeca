package com.example.jogotecaintellij.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.enums.StatusItemJogo;
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

    public void atualizarItemJogo(Jogo jogo) throws ElementDoesNotExistException {
        JogoController jc = JogoController.getInstance();
        ItemJogo itemAntigo = searchGameItemByGameId(jogo.getId());
        itemAntigo.setDescription(jogo.getDescription());
        itemAntigo.setDesenvolvedora(jogo.getDesenvolvedora());
        itemAntigo.setPublicadora(jogo.getPublicadora());
        itemAntigo.setImageURL(jogo.getImageURL());
        itemAntigo.setPrice(jogo.getPrice());
        itemAntigo.setVideoUrl(jogo.getVideoUrl());
        itemAntigo.setReleaseDate(jogo.getReleaseDate());
        itemAntigo.setGenre(jogo.getGenre());
        gameItemRepository.update(itemAntigo); // AGORA É ITEM ATUALIZADO
    }

    public void indisponibilizarAntigo(Jogo game) {
        // TRATAMENTO DA ATUALIZAÇÃO DE JOGOS EM RELAÇÃO A JOGOS A VENDA AO ATUALIZAR
        // UM JOGO UM NOVO ITEM É CRIADO, E O ANTIGO É INDISPONIBILIZADO PELO ENUM StatusItemJogo
        Optional<ItemJogo> primeiroItemJogoDisponivel = gameItemRepository.read()
                .stream()
                .filter(i -> i.getStatus() == StatusItemJogo.DISPONIVEL
                        && i.getGame().getId() == game.getId())
                .findFirst();
        // OPTIONAL DE DO PRIMEIRO JOGO DISPONIVEL ACHADO
        // NO CASO É TAMBÉM O ULTIMO QUE FOI REGISTRADO
        if (primeiroItemJogoDisponivel.isPresent()) {
            try {
                System.out.println("Entrou no 'if' em indisponibilizarAntigoCriaAtualizado");
                ItemJogo ultimoJogoIgual = primeiroItemJogoDisponivel.get();
                ultimoJogoIgual.setStatus(StatusItemJogo.INDISPONIVEL);
                gameItemRepository.update(ultimoJogoIgual);
                // ATUALIZANDO ITEM ANTIGO PARA STATUS = INDISPONIVEL
                /* Não usado atualmente
                insertGameItem(game);
                // INSERINDO NOVO ITEM ATUALIZADO NA LOJA (ITENS JOGOS DISPONIVEIS)
                */
                System.out.println("acho que atualizou");
                System.out.println(searchGameItemById(searchAllGameItem().size()).getGame());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Esse foi o primeiro " + game.getName() + " a ser registrado");
    }

    public List<ItemJogo> searchAllItensJogosDisponiveis() throws ElementsDoNotExistException {
        return searchAllGameItem().stream().filter(x -> x.getStatus().equals(StatusItemJogo.DISPONIVEL)).collect(Collectors.toList());
    }

    public ItemJogo searchGameItemById(int id) throws ElementDoesNotExistException {
        return gameItemRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public ItemJogo searchGameItemByGameId(int id) throws ElementDoesNotExistException {
        return gameItemRepository.read().stream().filter(x -> x.getIdJogo() == id).findFirst().orElse(null);
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

    public void destroyGameItemByGameId(int id) throws ElementDoesNotExistException {
        ItemJogo item = gameItemRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        gameItemRepository.delete(item);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
}