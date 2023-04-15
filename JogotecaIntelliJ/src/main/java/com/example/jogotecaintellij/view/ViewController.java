package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.JogoController;
import com.example.jogotecaintellij.controller.SessaoUsuarioController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.Jogo;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ViewController {
    private Stage stage;
    protected static SessaoUsuarioController suc;
    // GERENCIADOR DE SESSOES DE USUARIOS

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
        String css = this.getClass().getResource("estilos/view.css").toExternalForm();
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
        irParaTela(event, "Pagamento.fxml");
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
    protected void irParaMeusJogos(ActionEvent event) throws IOException {
        irParaTela(event, "MeusJogos.fxml");
    }

    @FXML
    protected void irParaWishlist(ActionEvent event) throws IOException {
        irParaTela(event, "Wishlist.fxml");
    }

    @FXML
    protected void irParaPerfilDoUsuario(ActionEvent event) throws IOException {
        irParaTela(event, "PerfilDoUsuario.fxml");
    }

    protected void irParaMeusPedidos(ActionEvent event) throws IOException {
        irParaTela(event, "MeusPedidos.fxml");
    }

    protected void irParaComprovante(ActionEvent event) throws IOException {
        irParaTela(event, "Comprovante.fxml");
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

    public static void searchGameByNome(JogoController gc, TextField campo, ListView<Jogo> lista, Label log) {
        String nome = campo.getText();
        List<Jogo> gamesAchados = new ArrayList<>();
        if (nome != null) {
            try {
                Jogo n = gc.searchGameByName(nome);
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


    public static void searchGameById(JogoController gc, TextField campo, ListView<Jogo> lista, Label log) {
        int id = Integer.parseInt(campo.getText());
        List<Jogo> gamesAchados = new ArrayList<>();
        if (id > 0) {
            try {
                Jogo n = gc.searchGameById(id);
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

    public static void mostraUsuariosAchados(ListView<Usuario> listaUsers, List<Usuario> usersAchados) {
        ObservableList<Usuario> data = FXCollections.observableArrayList();
        data.addAll(usersAchados);

        listaUsers.setCellFactory(new Callback<ListView<Usuario>, ListCell<Usuario>>() {
            @Override
            public ListCell<Usuario> call(ListView<Usuario> param) {
                ListCell<Usuario> cell = new ListCell<Usuario>() {
                    @Override
                    protected void updateItem(Usuario achado, boolean btl) {
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


    public static void mostraGamesAchados(ListView<Jogo> listaJogos, List<Jogo> gamesAchados) {
        ObservableList<Jogo> data = FXCollections.observableArrayList();
        data.addAll(gamesAchados);

        listaJogos.setCellFactory(new Callback<ListView<Jogo>, ListCell<Jogo>>() {
            @Override
            public ListCell<Jogo> call(ListView<Jogo> param) {
                ListCell<Jogo> cell = new ListCell<Jogo>() {
                    @Override
                    protected void updateItem(Jogo achado, boolean btl) {
                        super.updateItem(achado, btl);
                        try {
                            if (achado != null) {
                                String imageUrl = achado.getImageURL(); // caminho relativo da imagem, por exemplo: "midias/imagem.jpg"
                                Path absoluto = Paths.get(imageUrl).toAbsolutePath();
                                System.out.println(absoluto.toUri().toString());
                                Image img = new Image(absoluto.toUri().toString());
                                ImageView imgview = new ImageView(img);
                                imgview.setFitWidth(100);
                                imgview.setFitHeight(100);
                                setGraphic(imgview);
                                setText(achado.toString());
                                setTextAlignment(TextAlignment.JUSTIFY);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                return cell;
            }
        });
        listaJogos.setItems(data);
    }

    protected void mostraGamesItensAchados(ListView<ItemJogo> listaJogos, List<ItemJogo> gamesAchados,
                                           boolean mostrarPreco, boolean mostrarPlay) {
        ObservableList<ItemJogo> data = FXCollections.observableArrayList();
        data.addAll(gamesAchados);
        listaJogos.setCellFactory(new Callback<ListView<ItemJogo>, ListCell<ItemJogo>>() {
            @Override
            public ListCell<ItemJogo> call(ListView<ItemJogo> param) {
                ListCell<ItemJogo> cell = new ListCell<ItemJogo>() {
                    @Override
                    protected void updateItem(ItemJogo achado, boolean btl) {
                        super.updateItem(achado, btl);
                        try {
                            if (achado != null) {
                                String imageUrl = achado.getGame().getImageURL(); // caminho relativo da imagem, por exemplo: "midias/imagem.jpg"
                                Path absoluto = Paths.get(imageUrl).toAbsolutePath();
                                Image img = new Image(absoluto.toString());
                                ImageView imgview = new ImageView(img);
                                imgview.setFitWidth(100);
                                imgview.setFitHeight(100);
                                String legenda = "";
                                if (mostrarPreco)
                                    legenda = legenda.concat("Preço: " + achado.getGame().getPrice() + "\n");
                                legenda = legenda.concat("Nome: " + achado.getGame().getName() + "\n");
                                legenda = legenda.concat("Gênero: " + achado.getGame().getGenre().name().toLowerCase());
                                setText(legenda);
                                setTextAlignment(TextAlignment.LEFT);

                                Button btnVerJogo = new Button("Ver Jogo");
                                btnVerJogo.setOnAction(event -> {
                                    try {
                                        suc.setItemCorrente(achado);
                                        irParaPerfilDoJogo(event);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                                HBox hbox = new HBox();
                                hbox.setAlignment(Pos.CENTER_LEFT);
                                hbox.getChildren().add(imgview);
                                hbox.getChildren().add(btnVerJogo);
                                if (mostrarPlay) {
                                    Button btnJogarAgora = new Button("Jogar Agora");
                                    // com mais tempo poderia haver a função de abrir o jogo
                                    hbox.getChildren().add(btnJogarAgora);
                                }
                                setGraphic(hbox);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
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
        escolherMidia(campoUrl, Arrays.asList("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
    }

    public static void escolherVideo(TextField campoUrl) {
        escolherMidia(campoUrl, Arrays.asList("Vídeos", "*.mp4", "*.avi", "*.mov", "*.wmv", "*.flv", "*.mkv"));
    }

    public static void escolherMidia(TextField campoUrl, List<String> fm) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Arquivo");
        Stage stage = (Stage) campoUrl.getScene().getWindow();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters()
                .add(new ExtensionFilter(fm.get(0), fm.get(1), fm.get(2), fm.get(3), fm.get(4), fm.get(5)));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Path pathAbsolute = selectedFile.toPath();
            Path pathBase = selectedFile.getParentFile().toPath();
            Path pathRelative = pathBase.relativize(pathAbsolute);
            campoUrl.setText("src/main/resources/com/example/jogotecaintellij/view/midias/" + pathRelative.toString());
            //file:///C:/Users/chris/Desktop/repo/Jogoteca/JogotecaIntelliJ/ O CAMINHO RELATIVO COMEÇA do root ao projeto
        }
    }
}
