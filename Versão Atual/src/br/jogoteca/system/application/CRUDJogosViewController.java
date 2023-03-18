package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
	protected TextField name;
	@FXML 
	protected TextField genre;
	@FXML 
	protected TextField subGenre;
	@FXML 
	protected TextArea description;
	@FXML 
	protected TextField price;
	@FXML 
	protected TextField releaseDate;
	@FXML 
	protected TextField urlImage;
	@FXML 
	protected ImageView image;
	@FXML
	protected AnchorPane telaInserir;
	
	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);

	}

	@FXML
	protected void mostrarOpcoesCREATE(ActionEvent event) {
		menuItens.setText("Inserir novo Jogo");
		telaInserir.setVisible(true);
	}
	@FXML
	protected void mostrarOpcoesREAD(ActionEvent event) {
		menuItens.setText("Buscar Jogo");
		telaInserir.setVisible(false);
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