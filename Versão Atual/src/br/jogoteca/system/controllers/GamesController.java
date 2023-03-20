package br.jogoteca.system.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;

public class GamesController {
	private int gameId;
	private GenericRepository<Game> gameRepository;

	private static GamesController instance;

	private GamesController() {
		this.gameRepository = new GenericRepository<>("games.dat");
	}

	public static GamesController getInstance() {
		if (instance == null) {
			instance = new GamesController();
		}
		return instance;
	}

	public void mostrarGameRepository() {
		if (!gameRepository.read().isEmpty())
			for (Game game : gameRepository.read())
				System.out.println(game.getName());
	}

	public String insertGame(String name, LocalDate releaseDate, Genre genre, String description, String imageURL,
			double price) {
		Game novo = new Game(++gameId, name, releaseDate, genre, description, imageURL, price);
		try {
			gameRepository.insert(novo);
			return "Novo jogo inserido com Sucesso";
		} catch (Exception e) {
			return "Erro: Um Jogo com o mesmo nome já existe";
		}
	}

	public Game searchGameById(int id) {
		return gameRepository.read().stream().filter(game -> game.getId() == id).findFirst().orElse(null);

	}

	public Game searchGameByName(String name) {
		return gameRepository.read().stream().filter(game -> game.getName().equals(name)).findFirst().orElse(null);
	}

	public List<Game> searchAllGames() {
		return gameRepository.read();
	}

	public List<Game> searchGamesByGenre(Genre genre) {
		return gameRepository.read().stream().filter(game -> game.getGenre() == genre).collect(Collectors.toList());
	}

	public void updateGame(int id) {

	}

	public void destroyGame(int id) {

	}
}
