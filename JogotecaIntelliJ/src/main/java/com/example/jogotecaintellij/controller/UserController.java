package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameCPFExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.GameItem;
import com.example.jogotecaintellij.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private ArrayList<User> users;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Para leitura e gera��o de arquivos
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

    public User searchUserByLogin2(String login) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getLogin().equals(login)).findFirst().orElse(null);
    }

    public User searchUserByName2(String nome) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
    }

    public List<User> searchAllUsers2() throws ElementsDoNotExistException {
        return userRepository.read();
    }

    public void adicionarWishlist(User user, GameItem item) throws ElementDoesNotExistException {
        user.getWishlist().add(item);
        userRepository.update(user);
    }

    public void removerDaWishlist(User user, GameItem item) throws ElementDoesNotExistException {
    }

    public void limparWishlist(User user) throws ElementDoesNotExistException {
    }

    public boolean checaLoginESenha2(String login, String senha) throws ElementDoesNotExistException {
        User user = searchUserByLogin2(login);
        if (senha.equals(user.getSenha()))
            return true;
        return false;
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
