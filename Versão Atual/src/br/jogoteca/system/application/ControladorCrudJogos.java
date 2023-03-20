package br.jogoteca.system.application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControladorCrudJogos {

	@FXML
    private ListView<String> listaDeNomes;

    @FXML
    private TextField name;

    @FXML
    void adicionarJogo(MouseEvent event) {
    		listaDeNomes.getItems().add(name.getText());
    }

    @FXML
    void removerJogo(MouseEvent event) {
    		int selectedID = listaDeNomes.getSelectionModel().getSelectedIndex();
    		listaDeNomes.getItems().remove(selectedID);
    }

	
	
	
}
