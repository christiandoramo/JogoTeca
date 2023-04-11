package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.itemJogoController;
import com.example.jogotecaintellij.controller.JogoController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.SessaoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FeedUsuario extends ViewController implements Initializable {
    @FXML
    protected Label readLog;
    @FXML
    protected TextField campoBuscarNome;
    @FXML
    protected MenuButton campoBuscarGenero;
    @FXML
    protected ListView<ItemJogo> listaDeJogos;

    protected void mostraGamesItensAchados(ListView<ItemJogo> listaJogos, List<ItemJogo> gamesAchados) {
        ObservableList<ItemJogo> data = FXCollections.observableArrayList();
        data.addAll(gamesAchados);

        listaJogos.setCellFactory(new Callback<ListView<ItemJogo>, ListCell<ItemJogo>>() {
            @Override
            public ListCell<ItemJogo> call(ListView<ItemJogo> param) {
                ListCell<ItemJogo> cell = new ListCell<ItemJogo>() {
                    @Override
                    protected void updateItem(ItemJogo achado, boolean btl) {
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
                            button.setOnAction(event -> {
                                try {
                                    suc.setItemCorrente(achado);
                                    irAoPerfilDoJogoSelecionado(event);
                                } catch (Exception e) {
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

    @FXML
    void irAoPerfilDoJogoSelecionado(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaPerfilDoJogo(event);
    }

    @FXML
    void deslogar(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaLogin(event);
        suc.deslogarUsuario();
    }

    @FXML
    void irParaWishList(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaWishlist(event);
    }

    @FXML
    void irParaPerfilUsuario(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaPerfilDoUsuario(event);
    }

    @FXML
    protected void listarTodos() {
        itemJogoController gic = itemJogoController.getInstance();
        try {
            List<ItemJogo> allGameItem = gic.searchAllGameItem();
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
        itemJogoController gic = itemJogoController.getInstance();
        try {
            List<ItemJogo> gamesAchados = gic.searchAllGameItem().stream()
                    .filter(x -> x.getGame().getGenre() == genero).collect(Collectors.toList());
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

    protected void searchGameItemByNome(JogoController gc, itemJogoController gic, TextField campo, ListView<ItemJogo> lista, Label log) {
        String nome = campo.getText();
        List<ItemJogo> gamesAchados = new ArrayList<>();
        if (nome != null) {
            try {
                ItemJogo n = gic.searchAllGameItem()
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
        itemJogoController gic = itemJogoController.getInstance();
        JogoController gc = JogoController.getInstance();
        this.searchGameItemByNome(gc, gic, campoBuscarNome, listaDeJogos, readLog);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewController AcessAreaController;
        preencheMenuGeneros(campoBuscarGenero);
        listarTodos();
    }
}
