package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class CRUDJogosViewController implements Initializable {
	@FXML
	protected MenuButton menuItens;
	@FXML
	protected MenuItem itemCriar;
	@FXML
	protected MenuItem itemBuscar;
	@FXML
	protected MenuItem itemRemover;
	@FXML
	protected MenuItem itemAtualizar;
	
	
	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);
	}

	@FXML
	protected void mostrarOpcoesCREATE(ActionEvent event) {
		menuItens.setText("Inserir novo Jogo");
	}
	@FXML
	protected void mostrarOpcoesREAD(ActionEvent event) {
		menuItens.setText("Buscar Jogo");
	}
	@FXML
	protected void mostrarOpcoesUPDATE(ActionEvent event) {
		menuItens.setText("Atualizar Jogo");
	}
	@FXML
	protected void mostrarOpcoesDESTROY(ActionEvent event) {
		menuItens.setText("Remover Jogo");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}