package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.ItemJogoController;
import com.example.jogotecaintellij.controller.JogoController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FeedUsuario extends ViewController implements Initializable {
    @FXML
    protected Label readLog;
    @FXML
    protected TextField campoBuscarNome;
    @FXML
    protected MenuButton campoBuscarGenero;
    @FXML
    protected ListView<ItemJogo> listaDeJogos;

    @FXML
    void deslogar(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaLogin(event);
        suc.deslogarUsuario();
    }

    @FXML
    void irParaWishList(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaWishlist(event);
    }

    @FXML
    void irParaPerfilUsuario(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaPerfilDoUsuario(event);
    }

    @FXML
    protected void listarTodos() {
        ItemJogoController ijc = ItemJogoController.getInstance();
        try {
            List<ItemJogo> allGameItem = ijc.searchAllItensJogosDisponiveis();// APENAS DISPONIVEIS EM FEED USUARIO
            if (!allGameItem.isEmpty()) {
                mostraGamesItensAchados(listaDeJogos, allGameItem, true, false);
                readLog.setVisible(false);
            } else
                throw new ElementsDoNotExistException(allGameItem);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                readLog.setText("Erro: Nenhum jogo encontrado");
            } else {
                readLog.setText("Ocorreu um erro. Tente novamente.");
            }
            readLog.setVisible(true);
        }
    }

    @FXML
    protected void buscarPorGenero() {
        Genre genero = (Genre) campoBuscarGenero.getUserData();
        ItemJogoController ijc = ItemJogoController.getInstance();
        try {
            List<ItemJogo> gamesAchados = ijc.searchAllItensJogosDisponiveis().stream()
                    .filter(x -> x.getGenre() == genero).collect(Collectors.toList());
            if (!gamesAchados.isEmpty()) {
                // ordem alfabetica funcionando - antes da criação de atributos Replicados em itemjogo
                // Comparator<GameItem> nameComparator =
                // Comparator.comparing(GameItem::getGame);
                // gamesAchados.sort(nameComparator);
                mostraGamesItensAchados(listaDeJogos, gamesAchados, true, false);
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

    protected void searchGameItemByNome(TextField campo, ListView<ItemJogo> lista, Label log) {
        ItemJogoController ijc = ItemJogoController.getInstance();
        String nome = campo.getText();
        List<ItemJogo> gamesAchados = new ArrayList<>();
        if (nome != null) {
            try {
                ItemJogo n = ijc.searchAllItensJogosDisponiveis()
                        .stream()
                        .filter(x -> x.getName().equals(nome))
                        .findFirst()
                        .orElse(null);
                if (n != null) {
                    gamesAchados.add(n);
                    this.mostraGamesItensAchados(lista, gamesAchados, true, false);
                    gamesAchados.forEach(action -> System.out.println(action.getName()));
                    log.setVisible(false);
                } else
                    throw new ElementDoesNotExistException(n);
            } catch (Exception e) {
                log.setText(e.getMessage());
                log.setVisible(true);
            }
        }
    }

    @FXML
    protected void buscarPorNome() {
        this.searchGameItemByNome(campoBuscarNome, listaDeJogos, readLog);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencheMenuGeneros(campoBuscarGenero);
        listarTodos();
    }
}
