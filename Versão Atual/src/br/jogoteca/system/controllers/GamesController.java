package br.jogoteca.system.controllers;

import java.time.LocalDate;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;

public class GamesController {
	private int GameId;
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
		Game novo = new Game(++GameId, name, releaseDate, genre, description, imageURL, price);
		try {
			gameRepository.insert(novo);
			return "Novo jogo inserido com Sucesso";
		} catch (Exception e) {
			return "Erro: Um Jogo com o mesmo nome já existe";
		}
	}

	public Game readGame(int id) {
		return null;
	}

	public Game searchGameByID(int id) {
		return null;
	}

	public Game searchGameByName(String name) {
		return null;
	}

	public List<Game> searchAllGames() {
		return null;
	}

	public List<Game> searchGamesByCategory(String genre, String subGenre, String description) {
		return null;
	}

	public void updateGame(int id) {

	}

	public void destroyGame(int id) {

	}
}
