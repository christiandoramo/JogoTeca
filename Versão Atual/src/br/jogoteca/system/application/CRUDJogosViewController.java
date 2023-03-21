package br.jogoteca.system.application;

import java.net.URL;
import java.time.LocalDate;
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

	String modoAtualizacao = "";
	String modoRemocao = "";

	@FXML
	protected void removerJogo() {
		if (modoRemocao.equals("id"))
			removerPorId();
		else if (modoRemocao.equals("name"))
			removerPorName();
	}

	protected void removerPorId() {
		int _id = Integer.parseInt(campoRemoverId.getText());
		
		gc.destroyGameById(_id);
	}

	protected void removerPorName() {
		String _name = campoRemoverNome.getText();
		gc.destroyGameByName(_name);
	}

	@FXML
	protected void atualizarJogo() {
		if (modoAtualizacao.equals("id"))
			atualizarPorId();
		else if (modoAtualizacao.equals("name"))
			atualizarPorName();
	}

	@FXML
	protected void buscarRemoverPorId() {
		ViewsController.searchGameById(gc, campoRemoverId, listaRemover, destroyLog);
		modoRemocao = "id";
	}

	@FXML
	protected void buscarRemoverPorName() {
		ViewsController.searchGameByNome(gc, campoRemoverNome, listaRemover, destroyLog);
		modoRemocao = "name";
	}

	@FXML
	protected void buscarAtualizarPorId() {
		ViewsController.searchGameById(gc, CampoAtualizarPorId, JogoAAtualizar, updateLog);
		modoAtualizacao = "id";
	}

	@FXML
	protected void buscarAtualizarPorName() {
		ViewsController.searchGameByNome(gc, CampoAtualizarPorNome, JogoAAtualizar, updateLog);
		modoAtualizacao = "name";
	}

	protected void atualizarPorId() {
		int _id = Integer.parseInt(CampoAtualizarPorId.getText());
		String _name = CampoTrocaNome.getText();
		Genre _genero = (Genre) CampoTrocaGenero.getUserData();
		String p = CampoTrocaPrice.getText();
		Double _price = p == null || p.equals("") ? null : Double.parseDouble(p);
		String _descricao = CampoTrocaDescricao.getText();
		String _image = CampoTrocaImage.getText();
		LocalDate _release = CampoTrocaReleaseDate.getValue();
		gc.updateGameById(_id, _name, _genero, _price, _descricao, _image, _release);
	}

	protected void atualizarPorName() {
		String _name = CampoAtualizarPorNome.getText();
		String _newName = CampoTrocaNome.getText();
		Genre _genero = (Genre) CampoTrocaGenero.getUserData();
		String p = CampoTrocaPrice.getText();
		Double _price = p == null || p.equals("") ? null : Double.parseDouble(p);
		String _descricao = CampoTrocaDescricao.getText();
		String _image = CampoTrocaImage.getText();
		LocalDate _release = CampoTrocaReleaseDate.getValue();
		gc.updateGameByName(_name, _newName, _genero, _price, _descricao, _image, _release);
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

	@FXML
	protected void searchGameByIdentificador() {
		ViewsController.searchGameById(gc, CampoBuscarId, listaJogos, readLog);
	}

	@FXML
	protected void searchGameByNome() {
		ViewsController.searchGameByNome(gc, CampoBuscarNome, listaJogos, readLog);
	}

	@FXML
	protected void searchGameByGenero() {
		Genre genero = (Genre) CampoBuscarGenero.getUserData();
		System.out.println(genero);
		if (genero != null) {
			List<Game> gamesAchados = gc.searchGamesByGenre(genero);
			if (!gamesAchados.isEmpty()) {
				ViewsController.mostraAchados(listaJogos, gamesAchados);
				for (Game game : gamesAchados) {
					System.out.println("id: " + game.getId());
					System.out.println("name: " + game.getName());
					System.out.println("preco: " + game.getPrice());
					System.out.println("genero: " + game.getGenre().name());
					System.out.println("lançamento: " + game.getReleaseDate().toString());
					System.out.println("descrição: " + game.getDescription());
				}
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
			for (Game game : allGames) {
				System.out.println("id: " + game.getId());
				System.out.println("name: " + game.getName());
				System.out.println("preco: " + game.getPrice());
				System.out.println("genero: " + game.getGenre().name());
				System.out.println("lançamento: " + game.getReleaseDate().toString());
				System.out.println("descrição: " + game.getDescription());
			}
			ViewsController.mostraAchados(listaJogos, allGames);
			readLog.setVisible(false);
		} else {
			readLog.setText("Jogo Não Encontrado");
			readLog.setVisible(true);
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

	protected void limparOperacaoCRUD(AnchorPane telaOperacional) {
		AnchorPane[] telasOperacionais = new AnchorPane[] { telaInserir, telaAtualizar, telaBuscar, telaRemover };
		modoAtualizacao = "";
		modoRemocao = "";
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
	protected void selecionarImagem() {
		ViewsController.escolherImagem(urlImage);
	}

	@FXML
	protected void selecionarImagemAtualizar() {
		ViewsController.escolherImagem(CampoTrocaImage);
	}

	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		limparTelaCRUD();
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ViewsController.desabilitarDatasFuturas(releaseDate);
		ViewsController.desabilitarDatasFuturas(CampoTrocaReleaseDate);
		ViewsController.preencheMenuGeneros(genres);
		ViewsController.preencheMenuGeneros(CampoBuscarGenero);
		ViewsController.preencheMenuGeneros(CampoTrocaGenero);
		ViewsController.controlaDouble(price);
		ViewsController.controlaInteiro(campoRemoverId);
		ViewsController.controlaInteiro(CampoBuscarId);
	}

}