package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.enums.StatusJogo;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Jogo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class JogoController {
    private IGenericRepository<Jogo> gameRepository;

    private static JogoController instance;

    private JogoController() {
        this.gameRepository = new GenericRepository<>("jogos.dat");
        mostrarGameRepository();
    }

    public static JogoController getInstance() {
        if (instance == null) {
            instance = new JogoController();
        }
        return instance;
    }

    public boolean contemNome(String nome) {
        return gameRepository.read().stream().anyMatch(item -> item.getName().equals(nome));
    }

    //apenas para testes
    public void mostrarGameRepository() {
        if (!gameRepository.read().isEmpty())
            for (Jogo game : gameRepository.read())
                System.out.println(game.getName());
    }
//(int id, String name, LocalDate releaseDate, Genre genre, String description, String publicadora, String desenvolvedora, Double price, String imageURL, String videoUrl, List<String> imagesUrl, StatusJogo status
    public void insertGame(String name, LocalDate releaseDate, Genre genre, String description, String publicadora, String desenvolvedora, Double price, String imageURL, String videoUrl, StatusJogo status) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
        Jogo novo = new Jogo( name, releaseDate, genre, description, publicadora, desenvolvedora, price, imageURL, videoUrl, status);
        gameRepository.insert(novo);
        ItemJogoController gic = ItemJogoController.getInstance();
        gic.insertGameItem(novo);
    }


    public Jogo searchGameById(int id) throws ElementDoesNotExistException {
        return gameRepository.read().stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }

    public Jogo searchGameByName(String name) throws ElementDoesNotExistException {
        return gameRepository.read().stream().filter(game -> game.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Jogo> searchAllGames() throws ElementsDoNotExistException {
        return gameRepository.read();
    }

    public List<Jogo> searchGamesByGenre(Genre genre) throws ElementsDoNotExistException {
        return gameRepository.read().stream().filter(game -> game.getGenre() == genre).collect(Collectors.toList());
    }

    public void updateGame(Jogo game, String name, Genre genre, Double price, String description, String imageURL,
                           LocalDate releaseDate) throws ElementDoesNotExistException {
        if (game != null) {
            if (name != null && !name.equals(""))
                game.setName(name);
            if (imageURL != null && !imageURL.equals(""))
                game.setImageURL(imageURL);
            if (description != null && !description.equals(""))
                game.setDescription(description);
            if (price != null && price != 0.0)
                game.setPrice(price);
            if (genre != null)
                game.setGenre(genre);
            if (releaseDate != null)
                game.setReleaseDate(releaseDate);
            gameRepository.update(game);
        }
    }

    public void updateGameById(int id, String name, Genre genre, Double price, String description, String imageURL,
                               LocalDate releaseDate) throws ElementDoesNotExistException {
        Jogo game = searchGameById(id);
        updateGame(game, name, genre, price, description, imageURL, releaseDate);
    }

    public void updateGameByName(String name, String newName, Genre genre, Double price, String description,
                                 String imageURL, LocalDate releaseDate)
            throws ElementWithSameNameExistsException, ElementDoesNotExistException {
        Jogo game = searchGameByName(name);
        updateGame(game, newName, genre, price, description, imageURL, releaseDate);
    }

    public void destroyGameById(int id) throws ElementDoesNotExistException {
        gameRepository.delete(searchGameById(id));
    }

    public void destroyGameByName(String name) throws ElementDoesNotExistException {
        gameRepository.delete(searchGameByName(name));
    }
}
