package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameCPFExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private ArrayList<Usuario> users;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Para leitura e gera��o de arquivos
    private GenericRepository<Usuario> userRepository;
    private static UsuarioController instance;
    //

     private UsuarioController() {
         this.userRepository = new GenericRepository<>("users.dat");
     }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    //
    public void insertUser2(String cpf, String nome, String endereco, String telefone, String email, String login,
                            String senha) throws ElementAlreadyExistsException, ElementWithSameCPFExistsException {
        Usuario novo = new Usuario( cpf, nome, endereco, telefone, email, login, senha, new ArrayList<>());
        userRepository.insert(novo);
    }

    public boolean contemCPF2(String cpf) {
        return userRepository.read().stream().anyMatch(item -> item.getCPF().equals(cpf));
    }

    public Usuario searchUserById2(int id) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public Usuario searchUserByCPF2(String cpf) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getCPF().equals(cpf)).findFirst().orElse(null);
    }

    public Usuario searchUserByLogin2(String login) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getLogin().equals(login)).findFirst().orElse(null);
    }

    public Usuario searchUserByName2(String nome) throws ElementDoesNotExistException {
        return userRepository.read().stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
    }

    public List<Usuario> searchAllUsers2() throws ElementsDoNotExistException {
        return userRepository.read();
    }

    public void adicionarWishlist(Usuario user, ItemJogo item) throws ElementDoesNotExistException {
        user.getWishlist().add(item);
        userRepository.update(user);
    }

    public void removerDaWishlist(Usuario user, ItemJogo item) {
        user.getWishlist().removeIf(x -> x.equals(item));
    }


    public void limparWishlist(Usuario user) throws ElementDoesNotExistException {
        user.setWishlist(new ArrayList<>());
    }
    public boolean emailJaRegistrado(String email) {
        return userRepository.read().stream().anyMatch(u -> u.getEmail().equals(email));
    }
    public boolean loginJaRegistrado(String login) {
        return userRepository.read().stream().anyMatch(u -> u.getLogin().equals(login));
    }
    public boolean cpfJaRegistrado(String  cpf) {
        return userRepository.read().stream().anyMatch(u -> u.getCPF().equals(cpf));
    }

    public boolean checaLoginESenha2(String login, String senha) throws ElementDoesNotExistException {
        Usuario user = searchUserByLogin2(login);
        return senha.equals(user.getSenha());
    }

    public void destroyUserById2(int id) throws ElementDoesNotExistException {
        Usuario user = searchUserById2(id);
        userRepository.delete(user);
    }
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public UserController() {
//        this.users = new ArrayList<User>();
//    }

    public void insertUser(Usuario user) {
        this.users.add(user);
    }

    public Usuario searchUserById(int id) {
        for (Usuario user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void updateUserById(int id, Usuario updatedUser) {
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

    public ArrayList<Usuario> getAllUsers() {
        return this.users;
    }
}
