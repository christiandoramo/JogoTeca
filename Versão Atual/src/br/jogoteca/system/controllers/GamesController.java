package br.jogoteca.system.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;

public class GamesController {
	private GenericRepository<Game> gameRepository;
	private int lastId = 1;

	private static GamesController instance;

	private GamesController() {
		this.gameRepository = new GenericRepository<>("games.dat");
		lastId = gameRepository.read().size();
		System.out.println(lastId);
	}

	public static GamesController getInstance() {
		if (instance == null) {
			instance = new GamesController();
		}
		return instance;
	}

	public boolean contemNome(String nome) {
		boolean contemNome = gameRepository.read().stream().anyMatch(item -> item.getName().equals(nome));
		return contemNome;
	}

	public void mostrarGameRepository() {
		if (!gameRepository.read().isEmpty())
			for (Game game : gameRepository.read())
				System.out.println(game.getName());
	}

	public void insertGame(String name, LocalDate releaseDate, Genre genre, String description, String imageURL,
			double price) {
		Game novo = new Game(lastId + 1, name, releaseDate, genre, description, imageURL, price);
		try {
			gameRepository.insert(novo);
			lastId++;

		} catch (Exception e) {
			System.out.println(e);
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

	public void updateGameById(int id, String name, Genre genre, Double price, String description, String imageURL,
			LocalDate releaseDate) {
		Game game = searchGameById(id);
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
		}
		try {
			gameRepository.update(game);
			System.out.println("Sucesso: O jogo foi atualizado");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateGameByName(String name, String newName, Genre genre, Double price, String description,
			String imageURL, LocalDate releaseDate) {
		Game game = searchGameByName(name);
		try {
			if (newName != null && !newName.equals(""))
				game.setName(newName);
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
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void destroyGameById(int id) {
		Game game = searchGameById(id);
		try {
			gameRepository.delete(game);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void destroyGameByName(String name) {
		Game game = searchGameByName(name);
		try {
			gameRepository.delete(game);
			lastId--;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
