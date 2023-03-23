package br.jogoteca.system.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.jogoteca.system.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private Button deleteButton;

	private List<Usuario> elements = new ArrayList<>();

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
			elements.remove(selectedIndex);
			elementListView.getItems().remove(selectedIndex);

		}
	}

	@FXML
	void updateButton(MouseEvent event) {
		int selectedIndex = elementListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {
			String id = nameTextField.getText();
			String pedido = descriptionTextField.getText();
			Usuario element = elements.get(selectedIndex);
			element.setId(id);
			element.setPedido(pedido);
			elementListView.getItems().set(selectedIndex, pedido);

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
