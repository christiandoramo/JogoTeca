package br.jogoteca.system.controllers;

import java.util.ArrayList;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameCPFExistsException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.User;

public class UserController {

	private ArrayList<User> users;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Para leitura e geração de arquivos
	private GenericRepository<User> userRepository;
	private static UserController instance;
	private int lastId;
	//

	// private UserController() {
	// this.userRepository = new GenericRepository<>("users.dat");
	// }
	//
	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	//
	public void insertUser2(String cpf, String nome, String endereco, String telefone, String email, String login,
			String senha) throws ElementAlreadyExistsException, ElementWithSameCPFExistsException {
		User novo = new User(lastId + 1, cpf, nome, endereco, telefone, email, login, senha, new ArrayList<>());
		userRepository.insert(novo);
		lastId++;
	}

	public boolean contemCPF2(String cpf) {
		return userRepository.read().stream().anyMatch(item -> item.getCPF().equals(cpf));
	}

	public User searchUserById2(int id) throws ElementDoesNotExistException {
		return userRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public User searchUserByCPF2(String cpf) throws ElementDoesNotExistException {
		return userRepository.read().stream().filter(x -> x.getCPF().equals(cpf)).findFirst().orElse(null);
	}

	public User searchUserByName2(String nome) throws ElementDoesNotExistException {
		return userRepository.read().stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
	}

	public List<User> searchAllUsers2() throws ElementsDoNotExistException {
		return userRepository.read();
	}

	public void destroyUserById2(int id) throws ElementDoesNotExistException {
		User user = searchUserById2(id);
		userRepository.delete(user);
		lastId--;
	}
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public UserController() {
		this.users = new ArrayList<User>();
		//
		this.userRepository = new GenericRepository<>("users.dat");
		lastId = userRepository.read().size();
		//
	}

	public void insertUser(User user) {
		this.users.add(user);
	}

	public User searchUserById(int id) {
		for (User user : this.users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public void updateUserById(int id, User updatedUser) {
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i).getId() == id) {
				this.users.set(i, updatedUser);
			}
		}
	}

	public void destroyUserById(int id) {
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i).getId() == id) {
				this.users.remove(i);
			}
		}
	}

	public ArrayList<User> getAllUsers() {
		return this.users;
	}
}
