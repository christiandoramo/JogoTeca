package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.model.ItemJogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilDoUsuario extends ViewController implements Initializable {

    @FXML
    void handleMeusJogos(ActionEvent event)throws IOException {
        irParaMeusJogos(event);
    }

    @FXML
    void handleMeusPedidos(ActionEvent event)throws IOException {
        irParaMeusPedidos(event);
    }

    @FXML
    void handleFeedUsuario(ActionEvent event) throws IOException {
        irParaFeedUsuario(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
