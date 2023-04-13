package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Wishlist extends ViewController implements Initializable {

    @FXML
    private ListView<ItemJogo> listaDeItens;
    @FXML
    private Label wishlistLog;

    @FXML
    void comprarSelecionados(ActionEvent event) throws IOException {
        if (!suc.getUsuarioCorrente().getWishlist().isEmpty()) {
            suc.setItensCorrentes(suc.getUsuarioCorrente().getWishlist());
            //suc.atualizarWishlist();só após o pagamento
            irParaPagamento(event);
        }
    }

//    void selecionarItensDaWishlist(){
//      É MELHOR REFAZER A SELEÇÃO DE ITENS A COMPRA E A REMOÇÃO DE ITENS DA WISHLIST
    // USANDO O SLELECTION MODEL
//    }

    @FXML
    void limparWishlist(ActionEvent event) throws IOException, ElementDoesNotExistException {
        suc.getUsuarioCorrente().setWishlist(new ArrayList<>());
        suc.atualizarWishlist();
        carregarWishlist();
    }

    void carregarWishlist() {
        wishlistLog.setText("Nenhum jogo registrado");
        if (suc.getUsuarioCorrente().getWishlist().isEmpty())
            wishlistLog.setVisible(false);
        mostraGamesItensAchados(listaDeItens, suc.getUsuarioCorrente().getWishlist());
    }

    @FXML
    void voltarAoMeuFeed(ActionEvent event) throws IOException {
        irParaFeedUsuario(event);
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
                            File file = new File(achado.getGame().getImageURL());
                            String imagePath = file.toURI().toString();
                            Image img = new Image(imagePath);
                            ImageView imgview = new ImageView(img);
                            imgview.setFitWidth(100);
                            imgview.setFitHeight(100);
                            String legenda = "";
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

                            Button btnRemover = new Button("Remover");
                            btnRemover.setOnAction(event -> {
                                try {
                                    suc.getUsuarioCorrente().getWishlist().remove(achado);
                                    suc.atualizarWishlist();
                                    carregarWishlist();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            hbox.getChildren().add(btnRemover);

                            ToggleButton btnSelecionar = new ToggleButton("Selecionar");
                            btnSelecionar.selectedProperty().addListener((obs, antigoValor, novoValor) -> {
                                if (novoValor) {
                                    // Botão foi selecionado
                                    suc.getUsuarioCorrente().getWishlist().add(achado);
                                } else {
                                    // Botão foi desselecionado
                                    suc.getUsuarioCorrente().getWishlist().remove(achado);
                                }
                                try{
                                    suc.atualizarWishlist();
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            });
                            hbox.getChildren().add(btnSelecionar);

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
        carregarWishlist();
    }
}
