package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.PedidoController;
import com.example.jogotecaintellij.controller.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class PerfilDoJogo extends ViewController implements Initializable {
    @FXML
    private Text descricao;
    @FXML
    private Text desenvolvedora;
    @FXML
    private Text genero;
    @FXML
    private ImageView imagemPerfil;
    @FXML
    private Text lancamento;
    @FXML
    private MediaView mediaView;
    @FXML
    private Text nome;
    @FXML
    private Text preco;
    @FXML
    private Text publicadora;
    @FXML
    private Button btnAdicionarWishlist;

    Media media;
    MediaPlayer mediaPlayer;

    UsuarioController uc = UsuarioController.getInstance();
    PedidoController pc = PedidoController.getInstance();

    @FXML
    protected void adicionarAMinhaWishlist(ActionEvent event) {
        try {
            suc.getUsuarioCorrente().getWishlist().add(suc.getItemCorrente());
            suc.atualizarWishlist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DesabilitarBotaoWishList();
    }

    protected void carregarImagem() {
        String caminhoDaImagem = suc.getItemCorrente().getGame().getImageURL();
        File arquivo = new File(caminhoDaImagem);
        Image imagem = new Image(arquivo.toURI().toString());
        imagemPerfil.setImage(imagem);
    }

    public void carregarVideo() {
        String caminhoDoVideo = suc.getItemCorrente().getGame().getVideoUrl();
        File arquivo = new File(caminhoDoVideo);
        media = new Media(arquivo.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setFitWidth(400);
        mediaPlayer.setMute(true);
        mediaPlayer.play();
    }

    void DesabilitarBotaoWishList() {
        boolean jaComprado;
        boolean jaContemNaWishList;
//        ToggleButton tg = new ToggleButton(); USAR TOGGLE AO INVES DE BUTTON  para adicionar e remover
//        tg.setSelected(true);
        jaComprado = pc.checaSeUmJogoJaFoiComprado(suc.getUsuarioCorrente(), suc.getItemCorrente());
        jaContemNaWishList = suc.getUsuarioCorrente().getWishlist().contains(suc.getItemCorrente());
        btnAdicionarWishlist.setDisable(jaComprado || jaContemNaWishList);
    }

    void DesabilitarBotaoComprarAgora() {
        boolean jaComprado = pc.checaSeUmJogoJaFoiComprado(suc.getUsuarioCorrente(), suc.getItemCorrente());
        btnAdicionarWishlist.setDisable(jaComprado);
    }

    @FXML
    void comprarAgora(ActionEvent event) throws IOException {
        irParaPedidoPagamento(event);
        ///////// lista de compra atual é adicionada aqui
        suc.setItensCorrentes(Collections.singletonList(suc.getItemCorrente()));
        // lista de um jogo só para comprar
    }

    @FXML
    void sairDoPerfilParaMeusJogos(ActionEvent event) throws IOException {
        suc.setItemCorrente(null);
        irParaMeusJogos(event);
    }

    @FXML
    void sairDoPerfilParaFeedUsuario(ActionEvent event) throws IOException {
        suc.setItemCorrente(null);
        irParaFeedUsuario(event);
    }

    void carregarTela() {
        carregarImagem();
        carregarVideo();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTela();
        DesabilitarBotaoWishList();
        DesabilitarBotaoComprarAgora();
    }
}
