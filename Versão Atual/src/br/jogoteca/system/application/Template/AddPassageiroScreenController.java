package br.ufrpe.flight_system.gui;

import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.negocio.Fachada;
import br.ufrpe.flight_system.negocio.beans.Passageiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPassageiroScreenController {
    
    @FXML private TextField txtNome;
    @FXML private TextField txtUltimoNome;
    @FXML private TextField txtCPF;
    @FXML private TextField txtPassaporte;
    
    @FXML
    public void btnSalvarClicked(ActionEvent event) {
        Passageiro p = new Passageiro(this.txtNome.getText(), 
                this.txtUltimoNome.getText(), this.txtPassaporte.getText(), this.txtCPF.getText());
        try {
            Fachada.getInstance().inserirPassageiro(p);
        } catch (ElementoJaExisteException e) {
            // TODO Tratar exceção apresentando mensagem para o usuário
        }        
        ScreenManager.getInstance().getMainScreenController().updateListaPassageiros();
        this.limparCampos();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    public void btnCancelarClicked(ActionEvent event) {
        this.limparCampos();
        ((Stage) this.txtNome.getScene().getWindow()).close();
    }
    
    public void limparCampos() {
        this.txtNome.setText("");
        this.txtUltimoNome.setText("");
        this.txtCPF.setText("");
        this.txtPassaporte.setText("");
    }

}
