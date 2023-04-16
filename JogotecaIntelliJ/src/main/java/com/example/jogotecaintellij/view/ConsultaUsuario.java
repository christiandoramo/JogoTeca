package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.UsuarioController;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaUsuario  extends ViewController implements Initializable {

    @FXML
    private TextField campoBuscarId;

    @FXML
    private TextField campoBuscarCpf;

    @FXML
    private TextField campoBuscarNome;

    @FXML
    private ListView<Usuario> userListView;

    @FXML
    private Label logBuscas;

    UsuarioController uc = UsuarioController.getInstance();

    @FXML
    void searchPorCpf(ActionEvent event) {
        String cpf = campoBuscarCpf.getText();
        List<Usuario> usersAchados = new ArrayList<>();
        if (cpf != null && !cpf.equals("")) {
            try {
                Usuario n = uc.searchUserByCPF2(campoBuscarCpf.getText());
                if (n != null) {
                    usersAchados.add(n);
                    ViewController.mostraUsuariosAchados(userListView, usersAchados);
                    logBuscas.setVisible(false);
                    System.out.println(n.getCPF());
                } else
                    throw new ElementDoesNotExistException(n);
            } catch (Exception e) {
                logBuscas.setText("ERRROO");
                logBuscas.setVisible(true);
                if (e instanceof ElementDoesNotExistException) {
                    logBuscas.setText("Erro: Usuario não encontrado");
                } else {
                    logBuscas.setText("Ocorreu um erro. Tente novamente.");
                }
            }
        } else {
            logBuscas.setText("Erro: Digite um CPF válido");
            logBuscas.setVisible(true);
        }
    }

    @FXML
    void searchPorId(ActionEvent event) {
        int id = Integer.parseInt(campoBuscarId.getText());
        List<Usuario> usersAchados = new ArrayList<>();
        if (id > 0) {
            try {
                Usuario n = uc.searchUserById2(id);
                if (n != null) {
                    usersAchados.add(n);
                    ViewController.mostraUsuariosAchados(userListView, usersAchados);
                    logBuscas.setVisible(false);
                    System.out.println(n.getCPF());
                } else {
                    System.out.println("id nao achado");
                    throw new ElementDoesNotExistException(n);
                }
            } catch (Exception e) {
                if (e instanceof ElementDoesNotExistException) {
                    logBuscas.setText("Erro: Usuario não encontrado");
                } else {
                    logBuscas.setText("Ocorreu um erro. Tente novamente.");
                }
                logBuscas.setVisible(true);
            }
        } else {
            logBuscas.setText("Erro: Digite um ID válido");
            logBuscas.setVisible(true);
        }
    }

    @FXML
    void searchPorNome(ActionEvent event) {
        String nome = campoBuscarNome.getText();
        List<Usuario> usersAchados = new ArrayList<>();
        if (nome != null && !nome.equals("")) {
            try {
                Usuario n = uc.searchUserByName2(nome);
                if (n != null) {
                    usersAchados.add(n);
                    ViewController.mostraUsuariosAchados(userListView, usersAchados);
                    logBuscas.setVisible(false);
                    System.out.println(n.getCPF());
                } else
                    throw new ElementDoesNotExistException(n);
            } catch (Exception e) {
                if (e instanceof ElementDoesNotExistException) {
                    logBuscas.setText("Erro: Usuario não encontrado");
                } else {
                    logBuscas.setText("Ocorreu um erro. Tente novamente.");
                }
                logBuscas.setVisible(true);
            }
        } else {
            logBuscas.setText("Erro: Digite um Nome válido");
            logBuscas.setVisible(true);
        }
    }

    @FXML
    void searchTodos(ActionEvent event) {
        try {
            List<Usuario> allUsers = uc.searchAllUsers2();
            if (!allUsers.isEmpty()) {
                ViewController.mostraUsuariosAchados(userListView, allUsers);
                logBuscas.setVisible(false);
            } else
                throw new ElementsDoNotExistException(allUsers);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                logBuscas.setText("Erro: Nenhum usuário encontrado");
            } else {
                logBuscas.setText("Ocorreu um erro. Tente novamente.");
            }
            logBuscas.setVisible(true);
        }
    }

    @FXML
    void voltarParaPrincipalAdmin(ActionEvent event) throws IOException {
        irParaTela(event, "MenuAdmin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
