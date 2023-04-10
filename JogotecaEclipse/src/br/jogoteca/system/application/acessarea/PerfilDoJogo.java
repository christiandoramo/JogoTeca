package br.jogoteca.system.application.acessarea;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.PedidoController;
import br.jogoteca.system.controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PerfilDoJogo extends AcessAreaController implements Initializable {
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
//	@FXML
//	private MediaView mediaView;
	@FXML
	private Text nome;
	@FXML
	private Text preco;
	@FXML
	private Text publicadora;
	@FXML
	private Button btnWishList;

//	Media media;
//	MediaPlayer mediaPlayer;

	UserController uc = UserController.getInstance();
	PedidoController pc = PedidoController.getInstance();

	@FXML
	protected void adicionarWishlist(ActionEvent event) {
		// usar esse itemAtual como estático
		try {
			uc.adicionarWishlist(usuarioAtual, itemAtual);
		} catch (Exception e) {
		}
		DesabilitarBotaoWishList();
		itemAtual = null;
	}

	protected void carregarImagem() {
		String caminhoDaImagem = itemAtual.getGame().getImageURL();
		// String caminhoDaImagem = getClass().getResource("51EWX7C9B3L.jpg").getPath();
		File arquivo = new File(caminhoDaImagem);
		Image imagem = new Image(arquivo.toURI().toString());
		imagemPerfil.setImage(imagem);
	}

	public void carregarVideo() {
		/*
		String caminhoDoVideo = itemAtual.getGame().getVideoUrl();
		// String caminhoDoVideo =
		// getClass().getResource("PsxDigimonWorld2Trailer.mp4").getPath();
		File arquivo = new File(caminhoDoVideo);
		media = new Media(arquivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(400);
		mediaPlayer.setMute(true);
		mediaPlayer.play();
		*/
	}

	void DesabilitarBotaoWishList() {
			boolean jaComprado = pc.checaSeUmJogoJaFoiComprado(usuarioAtual, itemAtual);
			boolean jaContemNaWishList = usuarioAtual.getWishlist().contains(itemAtual);
			btnWishList.setDisable(jaComprado || jaContemNaWishList);
	}

	@FXML
	void comprarAgora(ActionEvent event) throws IOException {
		irParaPedidoPagamento(event);
		// leva o itemAtual até a compra
	}

	@FXML
	void sairDoPerfilParaMeusJogos(ActionEvent event) throws IOException {
		itemAtual = null;
		irParaMeusJogos(event);
	}

	@FXML
	void sairDoPerfilParaFeedUsuario(ActionEvent event) throws IOException {
		itemAtual = null;
		irParaFeedUsuario(event);
	}

	void carregarTela() {
		carregarImagem();
		//carregarVideo();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTela();
		DesabilitarBotaoWishList();
	}
}
