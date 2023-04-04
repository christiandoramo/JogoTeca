package br.jogoteca.system.application.acessarea;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
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
import javafx.stage.Stage;

public class CRUDJogosController extends AcessAreaController implements Initializable {

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
	protected void voltarParaMenuAdmin(ActionEvent event) throws IOException {
		limparTelaCRUD();
		//AcessAreaController.changeScreen(Tela.PRINCIPALADMIN);
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	handleBotaoIrMenuAdmin(event);
	}


	@FXML
	protected void removerJogo() {
		if (modoRemocao.equals("id"))
			removerPorId();
		else if (modoRemocao.equals("name"))
			removerPorName();
	}

	protected void removerPorId() {
		int _id = Integer.parseInt(campoRemoverId.getText());
		try {
			gc.destroyGameById(_id);
			destroyLog.setVisible(false);
		} catch (Exception e) {
			destroyLog.setText(e.getMessage());
			destroyLog.setVisible(true);
		}

	}

	protected void removerPorName() {
		String _name = campoRemoverNome.getText();
		try {
			gc.destroyGameByName(_name);
			destroyLog.setVisible(false);
		} catch (Exception e) {
			destroyLog.setText(e.getMessage());
			destroyLog.setVisible(true);
		}
	}

	@FXML
	protected void atualizarJogo() {
		if (preencheuAlgumaEntradaAtualizacao()) {
			if (modoAtualizacao.equals("id"))
				atualizarPorId();
			else if (modoAtualizacao.equals("name"))
				atualizarPorName();
			else
				updateLog.setText("Erro: Preencha algum campo para atualizar");
			updateLog.setVisible(true);
		}
	}

	@FXML
	protected void buscarRemoverPorId() {
		AcessAreaController.searchGameById(gc, campoRemoverId, listaRemover, destroyLog);
		modoRemocao = "id";
	}

	@FXML
	protected void buscarRemoverPorName() {
		AcessAreaController.searchGameByNome(gc, campoRemoverNome, listaRemover, destroyLog);
		modoRemocao = "name";
	}

	@FXML
	protected void buscarAtualizarPorId() {
		AcessAreaController.searchGameById(gc, CampoAtualizarPorId, JogoAAtualizar, updateLog);
		modoAtualizacao = "id";
	}

	@FXML
	protected void buscarAtualizarPorName() {
		AcessAreaController.searchGameByNome(gc, CampoAtualizarPorNome, JogoAAtualizar, updateLog);
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
		try {
			if (!gc.contemNome(_name)) {
				gc.updateGameById(_id, _name, _genero, _price, _descricao, _image, _release);
				updateLog.setText("Sucesso: O Jogo foi Atualizado");
			} else
				throw new ElementWithSameNameExistsException(_name);
		} catch (Exception e) {
			updateLog.setText(e.getMessage());
		} finally {
			updateLog.setVisible(true);
		}
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
		try {
			if (!gc.contemNome(_newName)) {
				gc.updateGameByName(_name, _newName, _genero, _price, _descricao, _image, _release);
				updateLog.setText("Sucesso: O Jogo foi Atualizado");
			} else
				throw new ElementWithSameNameExistsException(_newName);
		} catch (Exception e) {
			updateLog.setText(e.getMessage());
		}
		updateLog.setVisible(true);
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
			try {
				if (!gc.contemNome(nome)) {
					gc.insertGame(nome, lancamento, genero, descricao, url, preco);
					createLog.setText("Sucesso: Jogo inserido com sucesso");
				} else
					throw new ElementWithSameNameExistsException(nome);
			} catch (Exception e) {
				createLog.setText(e.getMessage());
			}
			gc.mostrarGameRepository();
		} else {
			createLog.setText("Erro: Preencha todos os Campos");
		}
		createLog.setVisible(true);

	}

	@FXML
	protected void searchGameByIdentificador() {
		AcessAreaController.searchGameById(gc, CampoBuscarId, listaJogos, readLog);
	}

	@FXML
	protected void searchGameByNome() {
		AcessAreaController.searchGameByNome(gc, CampoBuscarNome, listaJogos, readLog);
	}

	@FXML
	protected void searchGameByGenero() {
		Genre genero = (Genre) CampoBuscarGenero.getUserData();
		if (genero != null) {
			try {
				List<Game> gamesAchados = gc.searchGamesByGenre(genero);
				if (!gamesAchados.isEmpty()) {
					AcessAreaController.mostraGamesAchados(listaJogos, gamesAchados);
					readLog.setVisible(false);
				} else {
					throw new ElementsDoNotExistException(gamesAchados);
				}
			} catch (Exception e) {
				if (e instanceof ElementsDoNotExistException) {
					readLog.setText("Erro: Nenhum usuário encontrado");
				} else {
					readLog.setText("Ocorreu um erro. Tente novamente.");
				}
				readLog.setVisible(true);
			}
		} else {
			readLog.setText("Erro: Nenhum genero foi selecionado");
			readLog.setVisible(true);
		}
	}

	@FXML
	protected void searchTodos() {
		try {
			List<Game> allGames = gc.searchAllGames();
			if (!allGames.isEmpty()) {
				AcessAreaController.mostraGamesAchados(listaJogos, allGames);
				readLog.setVisible(false);
			} else
				throw new ElementsDoNotExistException(allGames);
		} catch (Exception e) {
			if (e instanceof ElementsDoNotExistException) {
				readLog.setText("Erro: Nenhum usuário encontrado");
			} else {
				readLog.setText("Ocorreu um erro. Tente novamente.");
			}
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

	public boolean preencheuAlgumaEntradaAtualizacao() {
		if (!CampoTrocaGenero.getText().trim().isEmpty())
			return true;
		if (!CampoTrocaGenero.getText().equals("Selecionar Genero"))
			return true;
		if (!CampoTrocaImage.getText().trim().isEmpty())
			return true;
		if (!CampoTrocaPrice.getText().trim().isEmpty())
			return true;
		if (CampoTrocaReleaseDate.getValue() != null)
			return true;
		if (!CampoTrocaDescricao.getText().trim().isEmpty())
			return true;
		return false;
	}

	@FXML
	protected void selecionarImagem() {
		AcessAreaController.escolherImagem(urlImage);
	}

	@FXML
	protected void selecionarImagemAtualizar() {
		AcessAreaController.escolherImagem(CampoTrocaImage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		AcessAreaController.desabilitarDatasFuturas(releaseDate);
		AcessAreaController.desabilitarDatasFuturas(CampoTrocaReleaseDate);
		AcessAreaController.preencheMenuGeneros(genres);
		AcessAreaController.preencheMenuGeneros(CampoBuscarGenero);
		AcessAreaController.preencheMenuGeneros(CampoTrocaGenero);
		AcessAreaController.controlaDouble(price);
		AcessAreaController.controlaInteiro(campoRemoverId);
		AcessAreaController.controlaInteiro(CampoBuscarId);
	}

}