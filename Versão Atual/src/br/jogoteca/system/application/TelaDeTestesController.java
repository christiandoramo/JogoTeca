package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TelaDeTestesController implements Initializable {
	@FXML
	protected void irParaCadastro(ActionEvent event) {
		ViewsController.changeScreen(Tela.CADASTRO);
	}
	@FXML
	protected void irParaLogin(ActionEvent event) {
		ViewsController.changeScreen(Tela.LOGIN);
	}
	@FXML
	protected void irParaPrincipalAdmin(ActionEvent event) {
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);
	}
	@FXML
	protected void irParaCRUDJogos(ActionEvent event) {
		ViewsController.changeScreen(Tela.CRUDJOGOS);
	}
	@FXML
	protected void irParaConsultaClientes(ActionEvent event) {
		ViewsController.changeScreen(Tela.CONSULTACLIENTES);
	}
	@FXML
	protected void irParaConsultaPedidos(ActionEvent event) {
		ViewsController.changeScreen(Tela.CONSULTAPEDIDOS);
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}