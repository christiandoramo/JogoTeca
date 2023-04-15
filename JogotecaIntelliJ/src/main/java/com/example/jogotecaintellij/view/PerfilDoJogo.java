package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Locale;
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
    private ToggleButton btnAdicionarWishlist;
    @FXML
    private Button btnComprarAgora;
    Media media;
    MediaPlayer mediaPlayer;

    @FXML
    protected void adicionarAMinhaWishlist(ActionEvent event) {

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

    public void carregarTextos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
        descricao.setText("Descrição: " + suc.getItemCorrente().getGame().getDescription());
        desenvolvedora.setText("Desenvolvedora: " + suc.getItemCorrente().getGame().getDesenvolvedora());
        genero.setText("Gênero: " + suc.getItemCorrente().getGame().getGenre().name());
        nome.setText("Nome: " + suc.getItemCorrente().getGame().getName());
        publicadora.setText("Publicadora: " + suc.getItemCorrente().getGame().getPublicadora());
        lancamento.setText("Data de Lançamento: " + suc.getItemCorrente().getGame().getReleaseDate().format(formatter));
        preco.setText("Preço: " + String.format("%.2f", suc.getItemCorrente().getGame().getPrice()));
    }

    void desabilitarBotoes() throws ElementsDoNotExistException {
        boolean jaComprado = suc.checaSeOJogoJaFoiComprado(suc.getItemCorrente());
        btnAdicionarWishlist.setDisable(jaComprado || suc.jogoJaAdicionadoAWishList(suc.getItemCorrente()));
        btnComprarAgora.setDisable(jaComprado);
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
        carregarTextos();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        btnAdicionarWishlist.setOnAction(eventoLambda -> {
            try {
                if (!btnComprarAgora.isDisabled()) {
                    suc.atualizarWishlist();
                    if (btnAdicionarWishlist.isSelected()) {
                        suc.getUsuarioCorrente().getWishlist().add(suc.getItemCorrente());
                    } else if (!btnAdicionarWishlist.isSelected()) {
                        suc.getUsuarioCorrente().getWishlist().remove(suc.getItemCorrente());
                    }
                }
                suc.atualizarWishlist();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (suc.jogoJaAdicionadoAWishList(suc.getItemCorrente())) {
            btnAdicionarWishlist.setDisable(true);
        }

        try {
            desabilitarBotoes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        carregarTela();
    }
}
