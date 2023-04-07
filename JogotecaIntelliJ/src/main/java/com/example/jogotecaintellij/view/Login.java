package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.UserController;
import com.example.jogotecaintellij.exception.CredenciaisIncorretasException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends AccessAreaController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessageLabel.setVisible(false);
    }

    @FXML
    public void logar(ActionEvent event) {
        UserController uc = UserController.getInstance();
        String username = usernameField.getText();
        String password = passwordField.getText();
        try{
            if (uc.checaLoginESenha2(username,password)) {
                setUsuarioAtual(uc.searchUserByLogin2(username));
                irParaFeedUsuario(event);
            }else{
                throw new CredenciaisIncorretasException();
            }
        }catch(Exception e){
            // mostrar mensagem de erro caso ocorra
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);
        }
    }
}