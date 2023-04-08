package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.PedidoController;
import com.example.jogotecaintellij.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilDoJogo extends AccessAreaController implements Initializable {
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
    private Button btnWishList;

    Media media;
    MediaPlayer mediaPlayer;

    UserController uc = UserController.getInstance();
    PedidoController pc = PedidoController.getInstance();

    @FXML
    protected void adicionarWishlist(ActionEvent event) {
        // usar esse novoItem como estático
        try {
            uc.adicionarWishlist(getUsuarioAtual(), getNovoItem());
        } catch (Exception e) {
        }
        DesabilitarBotaoWishList();
        setNovoItem(null);
    }

    protected void carregarImagem() {
//        String caminhoDaImagem = getNovoItem().getGame().getImageURL();
        String caminhoDaImagem = getClass().getResource("51EWX7C9B3L.jpg").getPath();
        File arquivo = new File(caminhoDaImagem);
        Image imagem = new Image(arquivo.toURI().toString());
        imagemPerfil.setImage(imagem);
    }

    public void carregarVideo() {
//       String videoPath = getNovoItem().getGame().getVideoUrl();
        String caminhoDoVideo = getClass().getResource("PsxDigimonWorld2Trailer.mp4").getPath();
        File arquivo = new File(caminhoDoVideo);
        media = new Media(arquivo.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setFitWidth(400);
        mediaView.setFitHeight(300);
        mediaPlayer.setMute(true);
        mediaPlayer.play();
    }

    void DesabilitarBotaoWishList() {
        if(getUsuarioAtual()!= null){
            boolean jaComprado = pc.checaSeUmJogoJaFoiComprado(getUsuarioAtual(), getNovoItem());
            boolean jaContemNaWishList = getUsuarioAtual().getWishlist().contains(getNovoItem());
            btnWishList.setDisable(jaComprado || jaContemNaWishList);
        }
    }

    @FXML
    void comprarAgora(ActionEvent event) throws IOException {
        irParaPedidoPagamento(event);
        //leva o novoItem até a compra
    }

    @FXML
    void sairDoPerfilParaMeusJogos(ActionEvent event) throws IOException {
        setNovoItem(null);
        irParaMeusJogos(event);
    }

    @FXML
    void sairDoPerfilParaFeedUsuario(ActionEvent event) throws IOException {
        setNovoItem(null);
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
    }
}
