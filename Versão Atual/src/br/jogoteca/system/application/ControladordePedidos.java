import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ControladordePedidos implements Initializable{
	
	
	
	
	 @FXML
	    private ListView<String> listV;

	    @FXML
	    private TextField name;

	    @FXML
	    void adicionar(MouseEvent event) {
	    		listV.getItems().add(name.getText());
	    }

	    @FXML
	    void remove(MouseEvent event) {
	    	int selectedID = listV.getSelectionModel().getSelectedIndex();
	    	listV.getItems().remove(selectedID);
	    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}






	