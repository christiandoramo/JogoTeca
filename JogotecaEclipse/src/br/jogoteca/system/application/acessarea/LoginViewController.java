package br.jogoteca.system.application.acessarea;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController extends AcessAreaController implements Initializable {
	
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
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();        
        
        
        // implementar lógica de autenticação
        
        // mostrar mensagem de erro caso ocorra
        errorMessageLabel.setText("Usuário ou senha inválidos");
        errorMessageLabel.setVisible(true);
    }
    
    @FXML
    public void OnRegisterButtonAction(ActionEvent event) throws IOException {
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        handleBotaoCadastro(event);
    }
}