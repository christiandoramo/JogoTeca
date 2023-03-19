package br.jogoteca.system.application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.models.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;

public class CRUDJogosViewController implements Initializable {

	@FXML
	protected MenuButton menuItens;
	
	@FXML
	protected AnchorPane telaInserir;
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
	protected ListView<ImageView> listaImagemJogo;
	
	
	
	@FXML
	protected AnchorPane telaBuscar;
	@FXML
	protected TextField CampoBuscarId;
	@FXML
	protected TextField CampoBuscarNome;
	@FXML
	protected ListView<ImageView> listaJogos;
	@FXML
	protected MenuButton CampoBuscarGenero;
	
	
	
	@FXML
	protected AnchorPane telaAtualizar;
	@FXML
	protected TextField CampoAtualizarPorId;
	@FXML
	protected TextField CampoAtualizarPorNome;
	@FXML
	protected TextField CampoTrocaNome;
	@FXML
	protected ListView<ImageView> JogoAAtualizar;
	@FXML
	protected MenuButton CampoTrocaGenero;
	@FXML
	protected TextField CampoTrocaImage;
	@FXML
	protected TextField CampoTrocaPrice;
	@FXML
	protected DatePicker CampoTrocaReleaseDate;
	@FXML
	protected TextArea CampoTrocaDescricao;
	
	
	
	@FXML
	protected AnchorPane telaRemover;
	@FXML
	protected TextField campoRemoverId;
	@FXML
	protected TextField campoRemoverNome;
	@FXML
	protected ListView<ImageView> listaRemover;
	
	@FXML
	protected Label createLog;
	@FXML
	protected Label readLog;
	@FXML
	protected Label updateLog;
	@FXML
	protected Label destroyLog;
	
	

	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);

	}

	@FXML
	protected void inserirJogo() {
		 String nome = name.getText();
		 Genre genero = (Genre) genres.getUserData();
		 String descricao = description.getText();
		 LocalDate lancamento = releaseDate.getValue();
		 String url = urlImage.getText();
		 double preco = Double.parseDouble(price.getText());
		 GamesController.insertGame(nome,lancamento,genero,descricao,url,preco);
		 
	}

	protected void limparOperacaoCRUD(AnchorPane telaOperacional) {
		AnchorPane[] telasOperacionais = new AnchorPane[] { telaInserir, telaAtualizar, telaBuscar, telaRemover };
		for (AnchorPane tela : telasOperacionais)
			if (tela.isVisible()) {
				tela.setVisible(false);
				for (Node node : telaOperacional.getChildren()) {
					if (node instanceof TextInputControl) {
						((TextInputControl) node).setText(null);
					} else if (node instanceof MenuButton) {
						((MenuButton) node).setText("Selecionar Genero");
					} else if (node instanceof DatePicker) {
						((DatePicker) node).setValue(null);
					}
				}
			}
		telaOperacional.setVisible(true);

	}

	@FXML
	protected void mostrarOpcoesCREATE(ActionEvent event) {
		menuItens.setText("Inserir novo Jogo");
		limparOperacaoCRUD(telaInserir);
	}

	@FXML
	protected void mostrarOpcoesREAD(ActionEvent event) {
		menuItens.setText("Buscar Jogo");
		limparOperacaoCRUD(telaBuscar);
	}

	@FXML
	protected void mostrarOpcoesUPDATE(ActionEvent event) {
		menuItens.setText("Atualizar Jogo");
		limparOperacaoCRUD(telaAtualizar);
	}

	@FXML
	protected void mostrarOpcoesDESTROY(ActionEvent event) {
		menuItens.setText("Remover Jogo");
		limparOperacaoCRUD(telaRemover);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Impede de escolher datas posteriores a hoje - FUNCIONANDO
		releaseDate.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date.isAfter(LocalDate.now())) {
					setDisable(true);
				}
			}
		});
		// cria os menuItems baseados nos enums genre - FUNCIONANDO
		Genre _genres[] = Genre.values();
		for (Genre genre : _genres) {
			MenuItem item = new MenuItem(genre.name());
			item.setUserData(genre);
			item.setOnAction(e -> {
				genres.setText(item.getText());
			});
			genres.getItems().add(item);
		}

		// apenas permite que sejam digitados doubles em price - FUNCIONANDO
		Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
		UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
			String newText = change.getControlNewText();
			if (validDoubleText.matcher(newText).matches()) {
				return change;
			}
			return null;
		};
		TextFormatter<Double> doubleFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, doubleFilter);
		price.setTextFormatter(doubleFormatter);

	}

}