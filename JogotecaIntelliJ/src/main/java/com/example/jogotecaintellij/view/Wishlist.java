package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.model.ItemJogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Wishlist extends ViewController implements Initializable {

    @FXML
    private Button btnComprarWishlist;

    @FXML
    private Button btnLimparWishlist;

    @FXML
    private ListView<ItemJogo> listaDeItens;
    @FXML
    private Label wishlistLog;

    @FXML
    void comprarWishlist(ActionEvent event) {

    }

    @FXML
    void limparWishlist(ActionEvent event) {

    }

    @FXML
    void voltarAoMeuFeed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
