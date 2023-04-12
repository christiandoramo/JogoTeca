package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.ItemJogoController;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MeusJogos extends ViewController implements Initializable {
    @FXML
    private ListView<ItemJogo> listaMeusJogos;
    @FXML
    private Label meusJogosLog;

    @FXML
    void irParaMeuPerfil(ActionEvent event) throws IOException {
        irParaPerfilDoUsuario(event);
    }
    protected void carregarListaDeJogos() {
        ItemJogoController gic = ItemJogoController.getInstance();
        try {
            List<ItemJogo> jogosDoUsuario = suc.buscarListaItensJogosCompradosUsuarioAtual();
            meusJogosLog.setText(jogosDoUsuario.size() + " jogos encontrados");
            if (!jogosDoUsuario.isEmpty()) {
                mostraGamesItensAchados(listaMeusJogos, jogosDoUsuario, false, true);
                meusJogosLog.setVisible(false);
            } else
                throw new ElementsDoNotExistException(jogosDoUsuario);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                meusJogosLog.setText("Erro: Nenhum jogo encontrado");
            } else {
                meusJogosLog.setText("Ocorreu um erro. Tente novamente.");
            }
            meusJogosLog.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarListaDeJogos();
    }
}
