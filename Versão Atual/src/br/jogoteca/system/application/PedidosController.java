package br.jogoteca.system.application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.PedidoController;

import br.jogoteca.system.exceptions.ElementDoesNotExistException;

import br.jogoteca.system.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PedidosController implements Initializable {

	@FXML
	private ListView<String> elementListView;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private Button addButton;

	@FXML
	private Button updateButton;
	
	@FXML
	private Button updateButton1;

	@FXML
	private Button deleteButton;
	
	@FXML
	private DatePicker datePicker;

	
	 @FXML
	 private TextField barraDeBusca;

	private List<Usuario> elements = new ArrayList<>();
	
	
	PedidoController pedidos = PedidoController.getInstance();
	
//teste
	//@FXML
	//void addButton(MouseEvent event) {
		//String id = nameTextField.getText();
		//String pedido = descriptionTextField.getText();		
		//Usuario element = new Usuario(id, pedido);
		//elements.add(element);
		//elementListView.getItems().add(element.getPedido());
		//elementListView.getItems().add(element.getId());		
		//pedidos.adicionarPedido(pedido,id);+
	//}
	@FXML
	void addButton(MouseEvent event) {
		String id = nameTextField.getText();
		String pedido = descriptionTextField.getText();		
		Usuario element = new Usuario(id, pedido);
		elements.add(element);
		elementListView.getItems().add(element.getPedido());
		elementListView.getItems().add(element.getId());		
		
	}
	 
		 	           	  
	
	@FXML
	void deleteButton(MouseEvent event) {
		int selectedIndex = elementListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {	
			elementListView.getItems().remove(selectedIndex);	
			elements.remove(selectedIndex);
		}
	}
	

	@FXML
	void updateButton(MouseEvent event) {
		int selectedIndex = elementListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {			
			String pedido = descriptionTextField.getText();			
			elementListView.getItems().set(selectedIndex, pedido);
			
			
		}
	}
	@FXML
	void updateButton1(MouseEvent event) {
	int selectedIndex = elementListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {
			String id = nameTextField.getText();								
			elementListView.getItems().set(selectedIndex,id);
						
		}
	}
	
	 @FXML
	    void buscar(MouseEvent event) throws ElementDoesNotExistException {
		 String searchText = barraDeBusca.getText().toLowerCase();		 
		 for (String item : elementListView.getItems()) {
			    if (item.toLowerCase().contains(searchText)) {
			    	elementListView.getSelectionModel().select(item);	
			    					
			    }
			}
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}