package br.jogoteca.system.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroViewController extends AcessAreaController implements Initializable {

	
	 @FXML
	    private TextField cpfTextField;
	 @FXML
	 private Label cpfLabel;
	    
	    @FXML
	    private TextField nomeTextField;
	    
	    @FXML
	    private TextField emailTextField;
	    @FXML
	    private Label emailLabel;
	    
	    @FXML
	    private Label nomeLabel;
	    
	    @FXML
	    private TextField telefoneTextField;
	    @FXML
	    private Label telefoneLabel;
	    
	    @FXML
	    private TextField enderecoTextField;
	    
	    @FXML
	    private Button confirmarButton;
	    
	    @FXML
	    private Button voltarButton;
	    
	    @FXML
	    private Label mensagemLabel;
	    

	    
	    @FXML
	    private void confirmar() {
	        String cpf = cpfTextField.getText();
	        String nome = nomeTextField.getText();
	        String email = emailTextField.getText();
	        String telefone = telefoneTextField.getText();
	        String endereco = enderecoTextField.getText();
	        boolean validaCpf, validaEmail;
	        
	        // Validações
	        if (!validarCPF(cpf)) {
	            cpfLabel.setText("CPF inválido");
	            validaCpf = false;
	        }else {
	            cpfLabel.setText("");
	            validaCpf = true;
	        }
	        if (nome.isEmpty()) {
	            nomeLabel.setText("Nome inválido");
	            
	        }else {
	            nomeLabel.setText("");	            
	        }
	        
	        
	        if (!validarEmail(email)) {
	            emailLabel.setText("Email inválido");	
	            validaEmail = false;
	        }else {
	            emailLabel.setText("");
	            validaEmail = true;
	        }
	        
	        // TODO: Validar telefone e demais campos
	        
	       if(validaCpf && validaEmail) {
	    	   mensagemLabel.setText("Cadastro realizado com sucesso!");
	       }
	       else {
	    	   mensagemLabel.setText("Não foi possivel realizar cadastro!");
	       }
	        
	        
	        // TODO: Salvar dados do cadastro
	    }
	    
	    private boolean validarEmail(String email) {
	        Pattern padraoEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	        return padraoEmail.matcher(email).matches();
	    }
	    
	    private boolean validarCPF(String cpf) {
	        cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
	        if (cpf.length() != 11) {
	            return false;
	        }
	        int soma = 0;
	        for (int i = 0; i < 9; i++) {
	            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
	        }
	        int digitoVerificador1 = 11 - (soma % 11);
	        if (digitoVerificador1 > 9) {
	            digitoVerificador1 = 0;
	        }
	        if (Character.getNumericValue(cpf.charAt(9)) != digitoVerificador1) {
	            return false;
	        }
	        soma = 0;
	        for (int i = 0; i < 10; i++) {
	            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
	        }
	        int digitoVerificador2 = 11 - (soma % 11);
	        if (digitoVerificador2 > 9) {
	            digitoVerificador2 = 0;
	        }
	        if (Character.getNumericValue(cpf.charAt(10)) != digitoVerificador2) {
	            return false;
	        }
	        return true;
	    }
	    
	    @FXML
	    private void OnHandleBotaoVoltar(ActionEvent event) throws IOException {
	        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
	        handleBotaoVoltar(event);
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			  telefoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
		            if (!newValue.matches("\\d*")) {
		                telefoneTextField.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        });
			  
			
		}

}
