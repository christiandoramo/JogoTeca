package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Cadastro extends ViewController implements Initializable {

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
    private TextField loginTextField;

    @FXML
    private TextField senhaTextField;
    @FXML
    private Label senhaLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label enderecoLabel;

    @FXML
    private void confirmar() {
        // controlador de usuario
        UsuarioController uc = UsuarioController.getInstance();

        String cpf = cpfTextField.getText();
        cpf = cpf.replaceAll("[^0-9]", "");
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String telefone = telefoneTextField.getText();
        String endereco = enderecoTextField.getText();
        String login = loginTextField.getText();
        String senha = senhaTextField.getText();

        boolean validaCpf, validaEmail;
        boolean tudoPreenchido = true;
        // o controlador de usuario deve criar o objeto user
        // public User(int id, String nome, String endereco, String telefone, String
        // email, String login, String senha)
        // User user = new User(0, nome, endereco, telefone, email, login, senha);
        // Valida��es
        if (!validarCPF(cpf)) {
            cpfLabel.setText("CPF inválido");
            validaCpf = false;
            tudoPreenchido = false;
        } else {
            cpfLabel.setText("");
            // user.setCPF(cpf);// após o cpf ser verificado
            validaCpf = true;
        }

        if (!validarEmail(email)) {
            emailLabel.setText("Email inválido");
            validaEmail = false;
            tudoPreenchido = false;
        } else {
            emailLabel.setText("");
            validaEmail = true;
        }

        // TODO: Validar telefone e demais campos
        ///////////////////////////////////////////
        if (nome.equals("") && nome != null) {
            nomeLabel.setText("Nome inválido");
            tudoPreenchido = false;
        } else {
            nomeLabel.setText("");
        }
        if (telefone.equals("") && telefone != null) {
            telefoneLabel.setText("telefone inválido");
            tudoPreenchido = false;
        } else {
            telefoneLabel.setText("");
        }
        if (endereco.equals("") && endereco != null) {
            enderecoLabel.setText("endereco inválido");
            tudoPreenchido = false;
        } else {
            enderecoLabel.setText("");
        }
        if (login.equals("") && login != null) {
            loginLabel.setText("login inválido");
            tudoPreenchido = false;
        } else {
            loginLabel.setText("");
        }
        if (senha.equals("") && senha != null) {
            senhaLabel.setText("senha inválido");
            tudoPreenchido = false;
        } else {
            senhaLabel.setText("");
        }
////////////////////////////////////////////////////
        if (validaCpf && validaEmail && tudoPreenchido) {
            // if(Cadastro.cadastrar(user)) {
            if (!uc.cpfJaRegistrado(cpf) && !uc.emailJaRegistrado(email) && !uc.loginJaRegistrado(login))
                try {
                    uc.insertUser2(cpf, nome, endereco, telefone, email, login, senha);
                    mensagemLabel.setText("Cadastro realizado com sucesso!");
                    telefoneTextField.setText("");
                    emailTextField.setText("");
                    enderecoTextField.setText("");
                    loginTextField.setText("");
                    senhaTextField.setText("");
                    cpfTextField.setText("");
                    nomeTextField.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            String erros = "";
            if (uc.cpfJaRegistrado(cpf))
                cpfLabel.setText("CPF já registrado");
            if (uc.loginJaRegistrado(login))
                loginLabel.setText("login já registrado");
            if (uc.emailJaRegistrado(email))
                emailLabel.setText("email já registrado");
            mensagemLabel.setText("Não foi possivel realizar cadastro!");
        }
    }

    private boolean validarEmail(String email) {
        Pattern padraoEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return padraoEmail.matcher(email).matches();
    }

    private boolean validarCPF(String cpf) {
        // String cpf � apenas uma copia da outra String cpf
        // cpf.replace deve ser atribuido a verdadeira variavel
        // por isso o bug do cpf invalido cadastro
        // cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres n�o num�ricos
        System.out.println(cpf);

        if (cpf.length() != 11) {
            return false;
        }
        /*// no momento ta bugando
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

        */
        return true;
    }

    @FXML
    private void OnHandleBotaoIrParaOLogin(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaLogin(event);
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
