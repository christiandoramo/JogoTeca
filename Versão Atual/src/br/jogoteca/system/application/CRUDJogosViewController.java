package br.jogoteca.system.application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
	protected ListView<Game> listaImagemJogo;

	@FXML
	protected AnchorPane telaBuscar;
	@FXML
	protected TextField CampoBuscarId;
	@FXML
	protected TextField CampoBuscarNome;
	@FXML
	protected ListView<Game> listaJogos;
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
	protected ListView<Game> JogoAAtualizar;
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
	protected ListView<Game> listaRemover;

	@FXML
	protected Label createLog;
	@FXML
	protected Label readLog;
	@FXML
	protected Label updateLog;
	@FXML
	protected Label destroyLog;

	GamesController gc = GamesController.getInstance();

	public boolean preencheuEntradasInsercao() {
		for (Node node : telaInserir.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField) node;
				if (tf.getText().trim().isEmpty()) {
					return false;
				}
			} else if (node instanceof DatePicker) {
				DatePicker dp = (DatePicker) node;
				if (dp.getValue() == null) {
					return false;
				}
			} else if (node instanceof MenuButton) {
				MenuButton mb = (MenuButton) node;
				if (mb.getText().equals("Selecionar Genero")) {
					return false;
				}
			}
		}
		return true;
	}

	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		limparTelaCRUD();
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);
	}

	@FXML
	protected void selecionarImagem() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir Arquivo");
		Stage stage = (Stage) urlImage.getScene().getWindow();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters()
				.add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			String absolutePath = selectedFile.getAbsolutePath();
			urlImage.setText(absolutePath);
		}
	}

	@FXML
	protected void inserirJogo() {
		if (preencheuEntradasInsercao()) {
			String nome = name.getText();
			Genre genero = (Genre) genres.getUserData();
			System.out.println(genero.name());
			String descricao = description.getText();
			LocalDate lancamento = releaseDate.getValue();
			String url = urlImage.getText();
			Double preco = Double.parseDouble(price.getText());

			createLog.setText(gc.insertGame(nome, lancamento, genero, descricao, url, preco));
			createLog.setVisible(true);
			gc.mostrarGameRepository();
		} else {
			createLog.setText("Erro: Preencha todos os Campos");
			createLog.setVisible(true);
		}
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
					} else if (node instanceof Label) {
						((Label) node).setVisible(false);
					}
				}
			}
		telaOperacional.setVisible(true);
	}

	protected void limparTelaCRUD() {
		AnchorPane[] telasOperacionais = new AnchorPane[] { telaInserir, telaAtualizar, telaBuscar, telaRemover };
		for (AnchorPane tela : telasOperacionais)
			if (tela.isVisible()) {
				tela.setVisible(false);
				for (Node node : tela.getChildren()) {
					if (node instanceof TextInputControl) {
						((TextInputControl) node).setText(null);
					} else if (node instanceof MenuButton) {
						((MenuButton) node).setText("Selecionar Genero");
					} else if (node instanceof DatePicker) {
						((DatePicker) node).setValue(null);
					} else if (node instanceof Label) {
						((Label) node).setVisible(false);
					}
				}
			}
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

	@FXML
	protected void searchGameByIdentificador() {
		int id = Integer.parseInt(CampoBuscarId.getText());
		List<Game> gamesAchados = new ArrayList<>();
		Game n = gc.searchGameById(id);
		if (n != null) {
			gamesAchados.add(n);
			ViewsController.mostraAchados(listaJogos, gamesAchados);
			readLog.setVisible(false);
		} else {
			readLog.setText("Jogo Não Encontrado");
			readLog.setVisible(true);
		}
	}

	@FXML
	protected void searchGameByNome() {
		String nome = CampoBuscarNome.getText();
		List<Game> gamesAchados = new ArrayList<>();
		if (nome != null) {
			Game n = gc.searchGameByName(nome);
			if (n != null) {
				gamesAchados.add(n);
				System.out.println(n.getGenre().name());
				ViewsController.mostraAchados(listaJogos, gamesAchados);
				gamesAchados.forEach(action -> System.out.println(action.getName()));
				readLog.setVisible(false);
			} else {
				readLog.setText("Jogo Não Encontrado");
				readLog.setVisible(true);
			}
		} else {
			readLog.setText("Digite um Nome");
			readLog.setVisible(true);
		}
	}

	@FXML
	protected void searchGameByGenero() {
		Genre genero = (Genre) CampoBuscarGenero.getUserData();
		System.out.println(genero);
		List<Game> gamesAchados;
		if (genero != null) {
			List<Game> n = gc.searchGamesByGenre(genero);
			for(Game g:n)
				System.out.println(g.getId());
			if (!n.isEmpty()) {
				gamesAchados = gc.searchGamesByGenre(genero);
				ViewsController.mostraAchados(listaJogos, gamesAchados);
				for(Game g:gamesAchados)
					System.out.println(g.getId());
				readLog.setVisible(false);
			} else {
				readLog.setText("Jogo Não Encontrado");
				readLog.setVisible(true);
			}
		}
	}

	@FXML
	protected void searchTodos() {
		List<Game> allGames = gc.searchAllGames();
		if (!allGames.isEmpty()) {
			ViewsController.mostraAchados(listaJogos, allGames);
			readLog.setVisible(false);
		} else {
			readLog.setText("Jogo Não Encontrado");
			readLog.setVisible(true);
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ViewsController.desabilitarDatasFuturas(releaseDate);
		ViewsController.preencheMenuGeneros(genres);
		ViewsController.preencheMenuGeneros(CampoBuscarGenero);
		ViewsController.controlaDouble(price);
		ViewsController.controlaDouble(CampoTrocaPrice);
		ViewsController.controlaInteiro(campoRemoverId);
		ViewsController.controlaInteiro(CampoBuscarId);
		ViewsController.controlaInteiro(CampoAtualizarPorId);
	}

}