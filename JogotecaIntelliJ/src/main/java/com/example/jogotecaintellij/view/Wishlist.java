package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Wishlist extends ViewController implements Initializable {
    @FXML
    private ListView<ItemJogo> listaDeItens;
    @FXML
    private Label wishlistLog;;
    @FXML
    private Button btnLimparWishlist;
    @FXML
    private Button btnComprarSelecionados;
    @FXML
    private AnchorPane anchorpaneWishlist;
    private Double valorSelecionado;

    @FXML
    void comprarSelecionados(ActionEvent event) {
        try {
            if (suc.retornaWishlistDisponiveis().isEmpty()) {
                wishlistLog.setVisible(true);
                wishlistLog.setText("Nenhum jogo salvo na Wishlist");
            } else if (suc.getItensCorrentes().isEmpty()) {
                wishlistLog.setVisible(true);
                wishlistLog.setText("Nenhum jogo foi selecionado");
            } else if (!suc.getItensCorrentes().isEmpty()) {
                // os itens correntes podem ser vazios ou nulos, diferente da wishlist
                // que é sempre não nula mas pode está vazia
                wishlistLog.setVisible(false);
                irParaPagamento(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void desabilitarBotoes() throws ElementDoesNotExistException {
        for (Node node : anchorpaneWishlist.getChildren())
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (btn.equals(btnComprarSelecionados) || btn.equals(btnLimparWishlist))
                    btn.setDisable(suc.retornaWishlistDisponiveis().isEmpty());
            }
        // se lista Vazia setaDisable recebe true senão recebe false
        // ou seja desabilita se vazia e habilita se não vazia
        // apenas os botoes: marcar desmarcar todos, comprar marcados e limpar wishlist
    }

    void desabilitarCompra() throws ElementDoesNotExistException {
        for (Node node : anchorpaneWishlist.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (btn.equals(btnComprarSelecionados)) {
                    btn.setDisable(suc.retornaWishlistDisponiveis().isEmpty());
                }
            }
        }
        // se lista Vazia setaDisable recebe true senão recebe false
        // ou seja desabilita se vazia e habilita se não vazia
        // apenas os botoes: marcar desmarcar todos, comprar marcados e limpar wishlist
    }

    @FXML
    void limparWishlist(ActionEvent event) throws IOException, ElementDoesNotExistException {
        suc.getUsuarioCorrente().setWishlist(new ArrayList<>());
        suc.atualizarWishlist();
        carregarWishlist();
    }

    void carregarWishlist() throws ElementDoesNotExistException {
        wishlistLog.setText("Nenhum jogo registrado");
        if (!suc.retornaWishlistDisponiveis().isEmpty())
            wishlistLog.setVisible(false);
        mostraGamesItensAchados(listaDeItens, suc.retornaWishlistDisponiveis());
        desabilitarBotoes();
    }

    @FXML
    void voltarAoMeuFeed(ActionEvent event) throws IOException {
        irParaFeedUsuario(event);
        suc.setItensCorrentes(null);
    }

    protected void mostraGamesItensAchados(ListView<ItemJogo> listaJogos, List<ItemJogo> gamesAchados) {
        // public por vai usar uma função local em uma não subclasse
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
                            Path imagePath = Paths.get(achado.getImageURL()).toAbsolutePath();
                            Image img = new Image(imagePath.toUri().toString());
                            ImageView imgview = new ImageView(img);
                            imgview.setFitWidth(100);
                            imgview.setFitHeight(100);
                            String legenda = "";
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

                            Button btnRemover = new Button("Remover");
                            btnRemover.setOnAction(event -> {
                                try {
                                    suc.retornaWishlistDisponiveis().remove(achado);
                                    suc.atualizarWishlist();
                                    carregarWishlist();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            hbox.getChildren().add(btnRemover);

                            // TOGGLEBOTAO SELECIONAR DESSELECIONAR
                            ToggleButton togbtnSelecionar = new ToggleButton("Selecionar");
                            togbtnSelecionar.selectedProperty().addListener((obs, antigoValor, novoValor) -> {
                                try {
                                    if (novoValor) {
                                        // Botão foi selecionado
                                        suc.getItensCorrentes().add(achado);
                                        valorSelecionado += achado.getPrice();
                                        wishlistLog.setText("Valor da Compra: " + String.format("%.2f", valorSelecionado));
                                        togbtnSelecionar.setText("Desselecionar");
                                    } else {
                                        // Botão foi desselecionado
                                        suc.getItensCorrentes().remove(achado);
                                        valorSelecionado -= achado.getPrice();
                                        wishlistLog.setText("Valor da Compra: " + String.format("%.2f", valorSelecionado));
                                        desabilitarCompra();//tenta desabilitar compra se não selecionou nenhum
                                        togbtnSelecionar.setText("Selecionar");
                                    }
                                    wishlistLog.setVisible(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            hbox.getChildren().add(togbtnSelecionar);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        });
        listaJogos.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valorSelecionado = 0.0;
        wishlistLog.setVisible(false);
        suc.setItensCorrentes(new ArrayList<>());
        try {
            desabilitarCompra();
            suc.getUsuarioCorrente().setWishlist(suc.retornaWishlistDisponiveis());
            suc.atualizarWishlist();
            carregarWishlist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
