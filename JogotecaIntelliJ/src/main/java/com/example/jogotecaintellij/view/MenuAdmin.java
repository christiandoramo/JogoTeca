package com.example.jogotecaintellij.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdmin extends ViewController implements Initializable {
	@FXML
	protected void btnirParaCRUDJogos(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaCRUDJogos(event);
	}
	@FXML
	protected void deslogarAdmin(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaLogin(event);
	}
	@FXML
	protected void btnirParaConsultaVendas(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaConsultaVendas(event);
	}
	@FXML
	protected void btnirParaConsultaUsuarios(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaConsultaUsuarios(event);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}
