package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.model.Pedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MeusPedidos extends ViewController implements Initializable {

    @FXML
    private TableView<Pedido> tabelaMeusPedidos;
    @FXML
    private Label meusPedidoLog;

    @FXML
    void voltarAoMeuPerfil(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
