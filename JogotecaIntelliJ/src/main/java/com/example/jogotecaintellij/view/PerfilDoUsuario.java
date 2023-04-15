package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.model.ItemJogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilDoUsuario extends ViewController implements Initializable {
    @FXML
    private Button meusJogosButton;

    @FXML
    private Button meusPedidosButton;
    @FXML
    private Button meuFeedButton;

    @FXML
    private Label nomeUsuarioLabel;

    @FXML
    private ImageView imagePerfil;

    @FXML
    private TextArea descricaoUsuarioLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField senhaTextField;

    @FXML
    void handleConfirmarAlteracoes(ActionEvent event) {

    }
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
