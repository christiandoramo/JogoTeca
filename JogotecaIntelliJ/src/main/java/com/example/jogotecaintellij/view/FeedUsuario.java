package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.GamesController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class FeedUsuario extends AccessAreaController implements Initializable {
    @FXML
    protected Label readLog;
    @FXML
    protected TextField campoBuscarNome;
    @FXML
    protected MenuButton campoBuscarGenero;
    @FXML
    protected ListView<Game> listaDeJogos;

//    @FXML
//    void clickouEmVerJogo(ActionEvent event) throws IOException{
//        setJogoAtual();


    //    }
    void irAoPerfilDoJogoSelecionado(MouseEvent event) throws IOException {
        irParaPerfilDoJogo(new ActionEvent());
    }

    @FXML
    void deslogar(ActionEvent event) throws IOException {
        usuarioAtual = null;
        irParaLogin(event);
    }

    @FXML
    void irParaWishList(ActionEvent event) throws IOException {
        irParaWishlist(event);
        jogoAtual = null;
    }

    @FXML
    void irParaPerfilUsuario(ActionEvent event) throws IOException {
        irParaPerfilDoUsuario(event);
    }

    @FXML
    protected void listarTodos() {
        GamesController gc = GamesController.getInstance();
        try {
            List<Game> allGames = gc.searchAllGames();
            if (!allGames.isEmpty()) {
                Comparator<Game> nameComparator = Comparator.comparing(Game::getName);
                allGames.sort(nameComparator);
                AccessAreaController.mostraGamesAchados(listaDeJogos, allGames);
                readLog.setVisible(false);
            } else
                throw new ElementsDoNotExistException(allGames);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                readLog.setText("Erro: Nenhum Jogo encontrado");
            } else {
                readLog.setText("Ocorreu um erro. Tente novamente.");
            }
            readLog.setVisible(true);
        }
    }


    @FXML
    protected void listarPorGenero() {
        Genre genero = (Genre) campoBuscarGenero.getUserData();
        GamesController gc = GamesController.getInstance();
        try {
            List<Game> gamesAchados = gc.searchGamesByGenre(genero);
            if (!gamesAchados.isEmpty()) {
                Comparator<Game> nameComparator = Comparator.comparing(Game::getName);
                gamesAchados.sort(nameComparator);
                AccessAreaController.mostraGamesAchados(listaDeJogos, gamesAchados);
                readLog.setVisible(false);
            } else
                throw new ElementsDoNotExistException(gamesAchados);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                readLog.setText("Erro: Nenhum Jogo encontrado");
            } else {
                readLog.setText("Ocorreu um erro. Tente novamente.");
            }
            readLog.setVisible(true);
        }
    }

    @FXML
    protected void buscarPorNome() {
        GamesController gc = GamesController.getInstance();
        AccessAreaController.searchGameByNome(gc, campoBuscarNome, listaDeJogos, readLog);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccessAreaController.preencheMenuGeneros(campoBuscarGenero);
        listarTodos();
        // carrega a função do clique na imagem para ir para o perfil
        listaDeJogos.setOnMouseClicked(event -> {
            try {
                jogoAtual= listaDeJogos.getSelectionModel().getSelectedItem();
                irAoPerfilDoJogoSelecionado(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
