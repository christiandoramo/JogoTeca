package com.example.jogotecaintellij.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdmin extends AccessAreaController implements Initializable {
	@FXML
	protected void btnirParaCRUDJogos(ActionEvent event) throws IOException {
		irParaCRUDJogos(event);
	}
	@FXML
	protected void btnirParaLogin(ActionEvent event) throws IOException {
		irParaLogin(event);
	}
	@FXML
	protected void btnirParaConsultaVendas(ActionEvent event) throws IOException {
		irParaConsultaVendas(event);
	}
	@FXML
	protected void btnirParaConsultaUsuarios(ActionEvent event) throws IOException {
		irParaConsultaUsuarios(event);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
