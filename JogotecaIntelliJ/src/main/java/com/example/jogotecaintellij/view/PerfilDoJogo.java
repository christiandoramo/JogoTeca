package com.example.jogotecaintellij.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;

public class PerfilDoJogo extends AccessAreaController implements Initializable {
    @FXML
    private Text descricao;
    @FXML
    private Text desenvolvedora;
    @FXML
    private Text genero;
    @FXML
    private ImageView imagemPerfil;
    @FXML
    private Text lancamento;
    @FXML
    private ListView<?> listaDeMidia;
    @FXML
    private Text nome;
    @FXML
    private Text preco;
    @FXML
    private Text publicadora;

    @FXML
    void adicionarWishlist(ActionEvent event) {
    }

    @FXML
    void comprarAgora(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

}
