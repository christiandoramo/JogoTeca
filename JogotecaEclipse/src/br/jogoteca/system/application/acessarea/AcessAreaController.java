package br.jogoteca.system.application.acessarea;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import br.jogoteca.system.controllers.GameItemControllers;
import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Genre;
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.User;
import br.jogoteca.system.models.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AcessAreaController {
private Stage stage;

// FALSO CONTROLADOR DE SESSOES DE USUARIO/////////////////////////////
protected static User usuarioAtual = null;
protected static List<GameItem> itensAtuais= null;
protected static GameItem itemAtual= null;
protected static Game jogoAtual = null;
protected static Pedido pedidoAtual = null;
protected static Venda vendaAtual = null;
protected static LocalDateTime vencimentoAtual = null;
protected static List<String> dadoAtual = null;
//FALSO CONTROLADOR DE SESSOES DE USUARIO/////////////////////////////
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void irParaPagamento(ActionEvent event) throws IOException {
        irParaTela(event, "Pagamento.fxml");
    }
    protected void irParaTela(ActionEvent event, String nomeArquivoFXML) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource(nomeArquivoFXML));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("view.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void irParaLogin(ActionEvent event) throws IOException {
        irParaTela(event, "Login.fxml");
    }

    @FXML
    protected void irParaCadastro(ActionEvent event) throws IOException {
        irParaTela(event, "Cadastro.fxml");
    }

    @FXML
    protected void irParaCRUDJogos(ActionEvent event) throws IOException {
        irParaTela(event, "CRUDJogos.fxml");
    }

    @FXML
    protected void irParaPedidoPagamento(ActionEvent event) throws IOException {
        irParaTela(event, "PedidoPagamento.fxml");
    }

    @FXML
    protected void irParaMenuAdmin(ActionEvent event) throws IOException {
        irParaTela(event, "MenuAdmin.fxml");
    }

    @FXML
    protected void irParaPerfilDoJogo(ActionEvent event) throws IOException {
    	
        irParaTela(event, "PerfilDoJogo.fxml");
    }

    @FXML
    protected void irParaFeedUsuario(ActionEvent event) throws IOException {
        irParaTela(event, "FeedUsuario.fxml");
    }

    @FXML
    protected void irParaConsultaUsuarios(ActionEvent event) {
        /*        irParaTela(event,"ConsultaUsuarios.fxml" );*/
    }

    @FXML
    protected void irParaConsultaVendas(ActionEvent event) {
        /*        irParaTela(event,"ConsultaVendas.fxml" );*/
    }

    @FXML
    protected void irParaMeusJogos(ActionEvent event) {
        /*	        irParaTela(event,"MeusJogos.fxml" );*/
    }

    @FXML
    protected void irParaWishlist(ActionEvent event) throws IOException {
        /*
                irParaTela(event,"Wishlist.fxml" );
        */
    }

    @FXML
    protected void irParaPerfilDoUsuario(ActionEvent event) throws IOException {
        /*
                irParaTela(event,"PerfilDoUsuario.fxml" );
        */
    }

    protected void irParaMeusPedidos(ActionEvent event) throws IOException {
        /*
                irParaTela(event,"MeusPedidos.fxml" );
        */
    }
    
    
    ///////////////// ACESS AREA CONTROLLER PARA CRUD DE JOGOS //////////////////////////////
    
	// apenas permite que sejam digitados doubles - FUNCIONANDO
	public static void controlaDouble(TextField tf) {
		Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
		UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
			String newText = change.getControlNewText();
			if (validDoubleText.matcher(newText).matches()) {
				return change;
			}
			return null;
		};
		TextFormatter<Double> doubleFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, doubleFilter);
		tf.setTextFormatter(doubleFormatter);
	}

	// apenas permite que sejam digitados int - FUNCIONANDO
	public static void controlaInteiro(TextField tf) {
		Pattern validIntText = Pattern.compile("-?\\d+");
		UnaryOperator<TextFormatter.Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			if (validIntText.matcher(newText).matches()) {
				return change;
			}
			return null;
		};
		TextFormatter<Integer> integerFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter);
		tf.setTextFormatter(integerFormatter);
	}

	// cria os menuItems baseados nos enums genre - FUNCIONANDO
	public static void preencheMenuGeneros(MenuButton mb) {
		Genre _genres[] = Genre.values();
		for (Genre genre : _genres) {
			MenuItem item = new MenuItem(genre.name());
			item.setUserData(genre);
			item.setOnAction(e -> {
				mb.setText(item.getText());
				mb.setUserData(genre);
			});
			mb.getItems().add(item);
		}
	}

	public static void searchGameByNome(GamesController gc, TextField campo, ListView<Game> lista, Label log) {
		String nome = campo.getText();
		List<Game> gamesAchados = new ArrayList<>();
		if (nome != null) {
			try {
				Game n = gc.searchGameByName(nome);
				if (n != null) {
					gamesAchados.add(n);
					mostraGamesAchados(lista, gamesAchados);
					gamesAchados.forEach(action -> System.out.println(action.getName()));
					log.setVisible(false);
				} else
					throw new ElementDoesNotExistException(n);
			} catch (Exception e) {
				log.setText(e.getMessage());
				log.setVisible(true);
			}
		}
	}
	
	public static void searchGameItemByNome(GamesController gc, GameItemControllers gic, TextField campo, ListView<GameItem> lista, Label log) {
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
					mostraGamesItensAchados(lista, gamesAchados);
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

	public static void searchGameById(GamesController gc, TextField campo, ListView<Game> lista, Label log) {
		int id = Integer.parseInt(campo.getText());
		List<Game> gamesAchados = new ArrayList<>();
		if (id > 0) {
			try {
				Game n = gc.searchGameById(id);
				if (n != null) {
					gamesAchados.add(n);
					mostraGamesAchados(lista, gamesAchados);
					log.setVisible(false);
				} else
					throw new ElementDoesNotExistException(n);
			} catch (Exception e) {
				log.setText(e.getMessage());
				log.setVisible(true);
			}
		} else {
			log.setText("Erro: Digite um ID");
			log.setVisible(true);
		}
	}

	public static void mostraUsuariosAchados(ListView<User> listaUsers, List<User> usersAchados) {
		ObservableList<User> data = FXCollections.observableArrayList();
		data.addAll(usersAchados);

		listaUsers.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
			@Override
			public ListCell<User> call(ListView<User> param) {
				ListCell<User> cell = new ListCell<User>() {
					@Override
					protected void updateItem(User achado, boolean btl) {
						super.updateItem(achado, btl);
						if (achado != null) {
							File file = new File(
									"C:\\Users\\chris\\Desktop\\repo\\Projeto-IP2\\Versão Atual\\src\\br\\jogoteca\\system\\data\\images\\51EWX7C9B3L.jpg");// perfis
																																							// não
																																							// possuem
																																							// //
																																							// imagem
							String imagePath = file.toURI().toString();
							Image img = new Image(imagePath);
							ImageView imgview = new ImageView(img);
							imgview.setFitWidth(100);
							imgview.setFitHeight(100);
							setGraphic(imgview);
							String legenda = "Id: " + achado.getId() + "\nNome: " + achado.getNome() + "\nCPF: "
									+ achado.getCPF();
							setText(legenda);
							setTextAlignment(TextAlignment.JUSTIFY);
						}
					}
				};
				return cell;

			}
		});
		listaUsers.setItems(data);
	}

	public static void mostraGamesItensAchados(ListView<GameItem> listaJogos, List<GameItem> gamesAchados) {
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
							setGraphic(imgview);
							String legenda = "Preço: " + achado.getGame().getPrice() + "\nNome: " + achado.getGame().getName();
							setText(legenda);
							setTextAlignment(TextAlignment.JUSTIFY);

						}
					}
				};
				return cell;
			}
		});
		listaJogos.setItems(data);
	}
	public static void mostraGamesAchados(ListView<Game> listaJogos, List<Game> gamesAchados) {
		ObservableList<Game> data = FXCollections.observableArrayList();
		data.addAll(gamesAchados);

		listaJogos.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
			@Override
			public ListCell<Game> call(ListView<Game> param) {
				ListCell<Game> cell = new ListCell<Game>() {
					@Override
					protected void updateItem(Game achado, boolean btl) {
						super.updateItem(achado, btl);
						if (achado != null) {
							File file = new File(achado.getImageURL());
							String imagePath = file.toURI().toString();
							Image img = new Image(imagePath);
							ImageView imgview = new ImageView(img);
							imgview.setFitWidth(100);
							imgview.setFitHeight(100);
							setGraphic(imgview);
							String legenda = "Id: " + achado.getId() + "\nNome: " + achado.getName();
							setText(legenda);
							setTextAlignment(TextAlignment.JUSTIFY);

						}
					}
				};
				return cell;
			}
		});
		listaJogos.setItems(data);
	}

	public static void desabilitarDatasFuturas(DatePicker dp) {
		dp.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date.isAfter(LocalDate.now())) {
					setDisable(true);
				}
			}
		});
	}

	public static void escolherImagem(TextField campoUrl) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir Arquivo");
		Stage stage = (Stage) campoUrl.getScene().getWindow();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters()
				.add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			String absolutePath = selectedFile.getAbsolutePath();
			campoUrl.setText(absolutePath);
		}
	}
    public static void escolherVideo(TextField campoUrl) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Arquivo");
        Stage stage = (Stage) campoUrl.getScene().getWindow();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters()
                .add(new ExtensionFilter("Vídeos", "*.mp4", "*.avi", "*.mov", "*.wmv", "*.flv", "*.mkv"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String absolutePath = selectedFile.getAbsolutePath();
            campoUrl.setText(absolutePath);
        }
    }
    ///////////////// ACESS AREA CONTROLLER PARA CRUD DE JOGOS //////////////////////////////
    
    
    
}

