package br.jogoteca.system.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.data.IGenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;

public class GamesController {
	private IGenericRepository<Game> gameRepository;
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
		return gameRepository.read().stream().anyMatch(item -> item.getName().equals(nome));
	}

	public void mostrarGameRepository() {
		if (!gameRepository.read().isEmpty())
			for (Game game : gameRepository.read())
				System.out.println(game.getName());
	}

	public void insertGame(String name, LocalDate releaseDate, Genre genre, String description, String imageURL,
			double price) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
		Game novo = new Game(lastId + 1, name, releaseDate, genre, description, imageURL, price);
		gameRepository.insert(novo);
		lastId++;
	}

	public Game searchGameById(int id) throws ElementDoesNotExistException {
		return gameRepository.read().stream().filter(game -> game.getId() == id).findFirst().orElse(null);
	}

	public Game searchGameByName(String name) throws ElementDoesNotExistException {
		return gameRepository.read().stream().filter(game -> game.getName().equals(name)).findFirst().orElse(null);
	}

	public List<Game> searchAllGames() throws ElementsDoNotExistException {
		return gameRepository.read();
	}

	public List<Game> searchGamesByGenre(Genre genre) throws ElementsDoNotExistException {
		return gameRepository.read().stream().filter(game -> game.getGenre() == genre).collect(Collectors.toList());
	}

	public void updateGameById(int id, String name, Genre genre, Double price, String description, String imageURL,
			LocalDate releaseDate) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
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
			gameRepository.update(game);
		}
	}

	public void updateGameByName(String name, String newName, Genre genre, Double price, String description,
			String imageURL, LocalDate releaseDate)
			throws ElementWithSameNameExistsException, ElementDoesNotExistException {
		Game game = searchGameByName(name);
		if (game != null) {
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
		}

	}

	public void destroyGameById(int id) throws ElementDoesNotExistException {
		Game game = searchGameById(id);
		try {
			gameRepository.delete(game);
			lastId--;
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void destroyGameByName(String name) throws ElementDoesNotExistException {
		Game game = searchGameByName(name);
		try {
			gameRepository.delete(game);
			lastId--;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
