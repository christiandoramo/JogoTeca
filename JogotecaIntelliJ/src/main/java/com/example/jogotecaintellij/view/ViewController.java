package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.JogoController;
import com.example.jogotecaintellij.controller.SessaoUsuarioController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Jogo;
import com.example.jogotecaintellij.model.Usuario;
import com.example.jogotecaintellij.model.Venda;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        Scene cenaAtual = ((Node) event.getSource()).getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeArquivoFXML));
        Parent novaCena = loader.load();
        cenaAtual.setRoot(novaCena);
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
    protected void irParaConsultaUsuarios(ActionEvent event) throws IOException {
        irParaTela(event, "ConsultaUsuario.fxml");
    }

    @FXML
    void irParaConsultaVendas(ActionEvent event) throws IOException {
        irParaTela(event, "ConsultaVendas.fxml");
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

    public static void preencheMenuGeneros(MenuButton mb) {
        // com combobox seria mais facil - mas consegui reusar o codigo facilmente
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
                log.setText("Jogo não encontrado");
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
                log.setText("Jogo não encontrado");
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
                            // perfis vão possuir imagem ??
                            String imageUrl = achado.getProfileUrl();
                            // caminho relativo da imagem, por exemplo: "midias/imagem.jpg"
                            Path absoluto = Paths.get(imageUrl).toAbsolutePath();
                            Image img = new Image(absoluto.toString());
                            ImageView imgview = new ImageView(img);
                            imgview.setFitWidth(100);
                            imgview.setFitHeight(100);
                            setGraphic(imgview);
                            String legenda = "Id: " + achado.getId() + "\nNome: " + achado.getNome() + "\nCPF: "
                                    + achado.getCpf();
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
                                String imageUrl = achado.getImageURL(); // caminho relativo da imagem, por exemplo: "midias/imagem.jpg"
                                Path absoluto = Paths.get(imageUrl).toAbsolutePath();
                                Image img = new Image(absoluto.toString());
                                ImageView imgview = new ImageView(img);
                                imgview.setFitWidth(100);
                                imgview.setFitHeight(100);
                                String legenda = "";
                                if (mostrarPreco)
                                    legenda = legenda.concat("Preço: " + achado.getPrice() + "\n");
                                legenda = legenda.concat("Nome: " + achado.getName() + "\n");
                                legenda = legenda.concat("Gênero: " + achado.getGenre().name().toLowerCase());
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

    public static void mostraVendasAchadas(ListView<Venda> listaVendas, List<Venda> usersAchados) {
        ObservableList<Venda> data = FXCollections.observableArrayList();
        data.addAll(usersAchados);
        listaVendas.setItems(data);
    }
}


