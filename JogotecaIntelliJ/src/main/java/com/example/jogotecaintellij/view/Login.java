package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.UsuarioController;
import com.example.jogotecaintellij.exception.CredenciaisIncorretasException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends ViewController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label errorMessageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessageLabel.setVisible(false);
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent event) throws IOException {
        UsuarioController uc = UsuarioController.getInstance();
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username);
        System.out.println(password);
        try {
            if (username.equals("admin") && password.equals("admin")) {
                errorMessageLabel.setVisible(false);
                setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
                irParaMenuAdmin(event);
            } else if (uc.searchUserByLogin2(username) == null)
                throw new ElementDoesNotExistException(username);
            else if (suc.logarUsuario(username, password)) {
                setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
                irParaFeedUsuario(event);
            } else
                throw new CredenciaisIncorretasException();
        } catch (CredenciaisIncorretasException e) {
            // mostrar mensagem de erro caso ocorra
            errorMessageLabel.setText("Usuário e/ou senha inválidos");
            errorMessageLabel.setVisible(true);
        } catch (ElementDoesNotExistException e) {
            errorMessageLabel.setText("Usuário não registrado");
            errorMessageLabel.setVisible(true);
        } catch (Exception e) {
            errorMessageLabel.setText("Ocorreu um erro tente mais tarde");
            errorMessageLabel.setVisible(true);
        }
    }

    @FXML
    public void OnRegisterButtonAction(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaCadastro(event);
    }
}