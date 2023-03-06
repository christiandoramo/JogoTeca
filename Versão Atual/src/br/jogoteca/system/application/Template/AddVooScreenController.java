package br.ufrpe.flight_system.gui;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.negocio.Fachada;
import br.ufrpe.flight_system.negocio.beans.Cidade;
import br.ufrpe.flight_system.negocio.beans.GmtZoneId;
import br.ufrpe.flight_system.negocio.beans.Voo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVooScreenController {
    
    @FXML ChoiceBox<Cidade> chBoxOrigem;
    @FXML ChoiceBox<Cidade> chBoxDestino;
    
    @FXML DatePicker dtPickerOrigem;
    @FXML DatePicker dtPickerDestino;
    
    @FXML TextField txtHoraOrigem;
    @FXML TextField txtHoraDestino;
    @FXML TextField txtNumAssentos;
    
    @FXML TextField txtMinutoOrigem;
    @FXML TextField txtMinutoDestino;

    @FXML ChoiceBox<GmtZoneId> chBoxGmtOrigem;
    @FXML ChoiceBox<GmtZoneId> chBoxGmtDestino;
    
    @FXML Button btnCancelarSalvarVoo;
    @FXML Button btnSalvarVoo;
    
    @FXML
    public void initialize() {
        this.chBoxOrigem.getItems().addAll(Cidade.values());
        this.chBoxDestino.getItems().addAll(Cidade.values());
        
        this.chBoxGmtOrigem.getItems().addAll(GmtZoneId.values());
        this.chBoxGmtDestino.getItems().addAll(GmtZoneId.values());
    }

    @FXML
    public void btnCancelarClicked(ActionEvent event) {
        ((Stage) this.btnCancelarSalvarVoo.getScene().getWindow()).close();
        this.limparCampos();
    }
    
    @FXML
    public void btnSalvarClicked(ActionEvent event) throws ElementoJaExisteException {
        if (!this.validarCampos()) {
            Alert a = new Alert(AlertType.ERROR); 
            a.setHeaderText("");
            a.setTitle("Erro");
            a.setContentText("Formulário apresenta campos inválidos");
            a.show();
            return;
        }
        Voo v = new Voo(Integer.parseInt(this.txtNumAssentos.getText()));
        v.setOrigem(this.chBoxOrigem.getValue());
        v.setDestino(this.chBoxDestino.getValue());
        v.setHorarioSaida(
            ZonedDateTime.of(this.dtPickerOrigem.getValue(), 
            LocalTime.of(Integer.parseInt(this.txtHoraOrigem.getText()), Integer.parseInt(this.txtMinutoOrigem.getText())), 
            ZoneId.of(this.chBoxGmtOrigem.getValue().toString())));
        
        v.setHorarioEstimadoChegada(
                ZonedDateTime.of(this.dtPickerDestino.getValue(), 
                LocalTime.of(Integer.parseInt(this.txtHoraDestino.getText()), Integer.parseInt(this.txtMinutoDestino.getText())), 
                ZoneId.of(this.chBoxGmtDestino.getValue().toString())));
        Fachada.getInstance().inserir(v);
        ((Stage) this.btnCancelarSalvarVoo.getScene().getWindow()).close();
        this.limparCampos();
        
        ScreenManager.getInstance().getMainScreenController().updateListaVoos();
    }
    
    private boolean validarCampos() {
        // TODO Escrever código de validação para inserção de novos voos verificando se todos os campos do formulário são válidos
        // 1 - nenhum campo pode estar em branco
        // 2 - valores númericos não podem conter texto
        // 3 - os campos de hora não podem conter valores diferentes de 0 - 23 para hora e 0 - 59 para minutos
        // 4 - a data e hora de chegada não pode ser anterior à data/hora de saída
        // 5 - o método deve retornar true se o formulário estiver válido
        return false;
    }
    
    public void limparCampos() {
        this.chBoxOrigem.getSelectionModel().clearSelection();
        this.chBoxDestino.getSelectionModel().clearSelection();
        this.chBoxGmtOrigem.getSelectionModel().clearSelection();
        this.chBoxGmtDestino.getSelectionModel().clearSelection();
        
        this.txtHoraOrigem.setText("");
        this.txtMinutoOrigem.setText("");
        this.txtHoraDestino.setText("");
        this.txtMinutoDestino.setText("");        
        
        this.dtPickerOrigem.setValue(null);
        this.dtPickerDestino.setValue(null);
        
        this.txtNumAssentos.setText("");
    }
}
