package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ListView<?> listaDeMidia;
    @FXML
    private Text nome;
    @FXML
    private Text preco;
    @FXML
    private Text publicadora;
    @FXML
    private Button btnWishList;

    UserController uc = UserController.getInstance();

    @FXML
    protected void adicionarWishlist(ActionEvent event) {
        // usar esse novoItem como estático
        try {
            uc.adicionarWishlist(getUsuarioAtual(), getNovoItem());
        } catch (Exception e) {
        }
        habilitarDesabilitarBotaoWishList(true);
        setNovoItem(null);
    }

    protected void carregarImagem() {
        String caminhoDaImagem = "caminho/para/minha/imagem.png";
        Image imagem = new Image(new File(caminhoDaImagem).toURI().toString());
        imagemPerfil.setImage(imagem);
    }

    void carregarListaDeImagens() {

    }

    void carregarVideo() {

    }
    void habilitarDesabilitarBotaoWishList(boolean jaAdicionado) {
        btnWishList.setDisable(jaAdicionado);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        if (getUsuarioAtual().getWishlist().contains(getNovoItem()))
            habilitarDesabilitarBotaoWishList(true);
        else
            habilitarDesabilitarBotaoWishList(false);
        carregarImagem();
        carregarVideo();
        carregarListaDeImagens();
    }
}
