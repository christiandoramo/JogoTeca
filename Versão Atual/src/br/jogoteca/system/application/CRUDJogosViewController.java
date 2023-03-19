package br.jogoteca.system.application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.models.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
	protected TextField name;
	@FXML 
	protected MenuButton genres;
	@FXML 
	protected TextArea description;
	@FXML 
	protected TextField price;
	@FXML 
	protected DatePicker releaseDate;
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
	CRUDJogosViewController(){
		Genre _genres[] = Genre.values();
		for(Genre genre: _genres) {
			MenuItem item = new MenuItem(genre.name());
			item.setUserData(genre);
			genres.getItems().add(item);
		}


	}
	@FXML
	protected void inserirJogo() {
//		String nome = name.getText();
//		Genre genero = Genre.UNKNOWN;
//		genres.setOnAction(event -> {
//		    MenuItem itemSelecionado = (MenuItem) event.getTarget();
//		    genero = (Genre) itemSelecionado.getUserData();
//		});
//		String descricao = description.getText();
//		LocalDate lancamento = releaseDate.getValue();
//		String url = urlImage.getText();
//		double preco = Double.parseDouble(price.getText());
//		GamesController controlador = new GamesController();
//		GamesController.insertGame(nome,lancamento,genero,descricao,url,preco);
		
	}

	@FXML
	protected void mostrarOpcoesCREATE(ActionEvent event) {
		menuItens.setText("Inserir novo Jogo");
		if(!telaInserir.isVisible())
			telaInserir.setVisible(true);
	}
	@FXML
	protected void mostrarOpcoesREAD(ActionEvent event) {
		menuItens.setText("Buscar Jogo");
		if(telaInserir.isVisible())
			telaInserir.setVisible(false);
	}
	@FXML
	protected void mostrarOpcoesUPDATE(ActionEvent event) {
		menuItens.setText("Atualizar Jogo");
		if(telaInserir.isVisible())
			telaInserir.setVisible(false);
	}
	@FXML
	protected void mostrarOpcoesDESTROY(ActionEvent event) {
		menuItens.setText("Remover Jogo");
		if(telaInserir.isVisible())
			telaInserir.setVisible(false);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}