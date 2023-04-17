package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.ItemJogoController;
import com.example.jogotecaintellij.controller.UsuarioController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PerfilDoUsuario extends ViewController implements Initializable {
    @FXML
    private Label emailAtual;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label enderecoAtual;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private ImageView imagePerfil;

    @FXML
    private Label logAlteracoes;

    @FXML
    private Label loginAtual;

    @FXML
    private Label nomeAtual;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField senhaRepetidaTextField;

    @FXML
    private TextField senhaTextField;

    @FXML
    private Label telefoneAtual;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private TextField urlImage;
    @FXML
    private Label logEmail;
    @FXML
    private Label logSenha;

    private void carregarDadosDaTela() {
        carregarImagem();
        emailAtual.setText(suc.getUsuarioCorrente().getEmail());
        loginAtual.setText(suc.getUsuarioCorrente().getLogin());
        nomeAtual.setText(suc.getUsuarioCorrente().getNome());
        enderecoAtual.setText(suc.getUsuarioCorrente().getEndereco());
        telefoneAtual.setText(suc.getUsuarioCorrente().getTelefone());
    }

    @FXML
    void escolherPerfil(ActionEvent event) throws IOException {
        ViewController.escolherImagem(urlImage);
    }

    void carregarImagem() {
        String caminhoDaImagem = suc.getUsuarioCorrente().getProfileUrl();
        File arquivo = new File(caminhoDaImagem);
        Image imagem = new Image(arquivo.toURI().toString());
        imagePerfil.setImage(imagem);
    }

    @FXML
    void handleConfirmarAlteracoes(ActionEvent event) throws IOException {
        atualizarUsuario();
        carregarDadosDaTela();
    }

    @FXML
    void handleFeedUsuario(ActionEvent event) throws IOException {
        irParaFeedUsuario(event);
    }

    @FXML
    void handleMeusJogos(ActionEvent event) throws IOException {
        irParaMeusJogos(event);
    }

    @FXML
    void handleMeusPedidos(ActionEvent event) throws IOException {
        irParaMeusPedidos(event);
    }


    private void atualizarUsuario() {
        boolean mudouAlgo = false;
        if (nomeTextField.getText() != null && !nomeTextField.getText().equals("")) {
            mudouAlgo = true;
            suc.getUsuarioCorrente().setNome(nomeTextField.getText());
        }
        if (senhaTextField.getText() != null && !senhaTextField.getText().equals("")) {
            if (senhaTextField.getText().equals(senhaRepetidaTextField.getText())) {
                mudouAlgo = true;
                suc.getUsuarioCorrente().setSenha(senhaTextField.getText());
            } else {
                logSenha.setVisible(true);
                logSenha.setText("Senhas digitadas não são iguais");
            }
        }
        if (emailTextField.getText() != null && !emailTextField.getText().equals("")) {
            if (validarEmail(emailTextField.getText())) {
                mudouAlgo = true;
                suc.getUsuarioCorrente().setEmail(emailTextField.getText());
            } else {
                logEmail.setVisible(true);
                logEmail.setText("Digite um email Válido");
            }
        }
        if (urlImage.getText() != null && !urlImage.getText().equals("")) {
            mudouAlgo = true;
            suc.getUsuarioCorrente().setProfileUrl(urlImage.getText());
        }
        if (enderecoTextField.getText() != null && !enderecoTextField.getText().equals("")) {
            mudouAlgo = true;
            suc.getUsuarioCorrente().setEndereco(enderecoTextField.getText());
        }
        if (telefoneTextField.getText() != null && !telefoneTextField.getText().equals("")) {
            mudouAlgo = true;
            suc.getUsuarioCorrente().setTelefone(telefoneTextField.getText());
        }
        try {
            logAlteracoes.setVisible(true);
            if (mudouAlgo) {
                UsuarioController uc = UsuarioController.getInstance();
                uc.updateUser2(suc.getUsuarioCorrente());
                logAlteracoes.setText("Informações atualizadas com sucesso");
            } else {
                logAlteracoes.setText("Nenhum modificação ocorreu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        emailTextField.setText(null);
        nomeTextField.setText(null);
        enderecoTextField.setText(null);
        urlImage.setText(null);
        telefoneTextField.setText(null);
        senhaTextField.setText(null);
        senhaRepetidaTextField.setText(null);
    }

    private boolean validarEmail(String email) {
        Pattern padraoEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return padraoEmail.matcher(email).matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logAlteracoes.setVisible(false);
        logSenha.setVisible(false);
        logEmail.setVisible(false);
        carregarDadosDaTela();
    }
}
