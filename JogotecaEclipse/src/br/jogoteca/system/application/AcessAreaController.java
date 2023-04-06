package br.jogoteca.system.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AcessAreaController {
private Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
	protected void handleBotaoCadastro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        Scene scene = new Scene(root);
        Stage cadastroStage = new Stage();
        cadastroStage.setScene(scene);
        cadastroStage.show();
        stage.close();
    }
    
    @FXML
    protected void handleBotaoVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

