package br.jogoteca.system.application.acessarea;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import br.jogoteca.system.controllers.GameItemControllers;
import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FeedUsuario extends AcessAreaController implements Initializable {
	@FXML
	protected Label readLog;
	@FXML
	protected TextField campoBuscarNome;
	@FXML
	protected MenuButton campoBuscarGenero;
	@FXML
	protected ListView<GameItem> listaDeJogos;
	
	protected void mostraGamesItensAchados(ListView<GameItem> listaJogos, List<GameItem> gamesAchados) {
		ObservableList<GameItem> data = FXCollections.observableArrayList();
		data.addAll(gamesAchados);

		listaJogos.setCellFactory(new Callback<ListView<GameItem>, ListCell<GameItem>>() {
			@Override
			public ListCell<GameItem> call(ListView<GameItem> param) {
				ListCell<GameItem> cell = new ListCell<GameItem>() {
					@Override
					protected void updateItem(GameItem achado, boolean btl) {
						super.updateItem(achado, btl);
						if (achado != null) {
							File file = new File(achado.getGame().getImageURL());
							String imagePath = file.toURI().toString();
							Image img = new Image(imagePath);
							ImageView imgview = new ImageView(img);
							imgview.setFitWidth(100);
							imgview.setFitHeight(100);
							String legenda = "Preço: " + achado.getGame().getPrice() + "\nNome: " + achado.getGame().getName();
							setText(legenda);
							setTextAlignment(TextAlignment.JUSTIFY);
							
				            Button button = new Button("Ver Jogo");
				            // Definição do evento de clique do botão
				            button.setOnAction(event -> {
				                // Chama a função funcionar() passando o objeto achado
				            	clickouEmVerJogo(achado);
				            	try {
					            	irAoPerfilDoJogoSelecionado(event);
				            	}catch(Exception e) {
				            		e.printStackTrace();
				            	}
				            });
				            
				            HBox hbox = new HBox();
				            hbox.setAlignment(Pos.CENTER_LEFT);
				            hbox.getChildren().add(imgview);
				            hbox.getChildren().add(button);
							setGraphic(hbox);
						}
					}
				};
				return cell;
			}
		});
		listaJogos.setItems(data);
	}

	
	void clickouEmVerJogo(GameItem item)  {
		itemAtual = item;
		jogoAtual = itemAtual.getGame();
	}
	
	@FXML
	void irAoPerfilDoJogoSelecionado(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaPerfilDoJogo(event);
	}

	@FXML
	void deslogar(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		usuarioAtual = null;
		irParaLogin(event);
	}

	@FXML
	void irParaWishList(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaWishlist(event);
		jogoAtual = null;
	}

	@FXML
	void irParaPerfilUsuario(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaPerfilDoUsuario(event);
	}

	@FXML
	protected void listarTodos() {
		GameItemControllers gic = GameItemControllers.getInstance();
		try {
			List<GameItem> allGameItem = gic.searchAllGameItem();
			if (!allGameItem.isEmpty()) {
				mostraGamesItensAchados(listaDeJogos, allGameItem);
				readLog.setVisible(false);
			} else
				throw new ElementsDoNotExistException(allGameItem);
		} catch (Exception e) {
			if (e instanceof ElementsDoNotExistException) {
				readLog.setText("Erro: Nenhum jogo encontrado");
			} else {
				readLog.setText("Ocorreu um erro. Tente novamente.");
			}
			readLog.setVisible(true);

		}
	}

	@FXML
	protected void buscarPorGenero() {
		Genre genero = (Genre) campoBuscarGenero.getUserData();
		GameItemControllers gic = GameItemControllers.getInstance();
		try {
			List<GameItem> gamesAchados = gic.searchAllGameItem().stream()
					.filter(x -> x.getGame().getGenre().equals(genero)).collect(Collectors.toList());
			if (!gamesAchados.isEmpty()) {
				// Comparator<GameItem> nameComparator =
				// Comparator.comparing(GameItem::getGame);
				// gamesAchados.sort(nameComparator);
				this.mostraGamesItensAchados(listaDeJogos, gamesAchados);
				readLog.setVisible(false);
			} else
				throw new ElementsDoNotExistException(gamesAchados);
		} catch (Exception e) {
			if (e instanceof ElementsDoNotExistException) {
				readLog.setText("Erro: Nenhum Jogo encontrado");
			} else {
				readLog.setText("Ocorreu um erro. Tente novamente.");
			}
			readLog.setVisible(true);
		}
	}

	protected void searchGameItemByNome(GamesController gc, GameItemControllers gic, TextField campo, ListView<GameItem> lista, Label log) {
		String nome = campo.getText();
		List<GameItem> gamesAchados = new ArrayList<>();
		if (nome != null) {
			try {
				GameItem n = gic.searchAllGameItem()
	                    .stream()
	                    .filter(x -> x.getGame().getName().equals(nome))
	                    .findFirst()
	                    .orElse(null);
				if (n != null) {
					gamesAchados.add(n);
					this.mostraGamesItensAchados(lista, gamesAchados);
					gamesAchados.forEach(action -> System.out.println(action.getGame().getName()));
					log.setVisible(false);
				} else
					throw new ElementDoesNotExistException(n);
			} catch (Exception e) {
				log.setText(e.getMessage());
				log.setVisible(true);
			}
		}
	}
	
	@FXML
	protected void buscarPorNome() {
		GameItemControllers gic = GameItemControllers.getInstance();
		GamesController gc = GamesController.getInstance();
		this.searchGameItemByNome(gc, gic, campoBuscarNome, listaDeJogos, readLog);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		AcessAreaController.preencheMenuGeneros(campoBuscarGenero);
		listarTodos();
		// carrega a funÃ§Ã£o do clique na imagem para ir para o perfil
	}
}
