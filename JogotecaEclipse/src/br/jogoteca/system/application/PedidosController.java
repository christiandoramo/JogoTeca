package br.jogoteca.system.application;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.jogoteca.system.controllers.PedidoController;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.models.Pedido;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PedidosController implements Initializable {


	 @FXML
    private ListView<Pedido> listPedido;

     @FXML
     private TextField textId;

   @FXML
    private TextField textItem;
    
    
    PedidoController ped = PedidoController.getInstance();
    
    
    
    
   

    @FXML
     void buscarId(MouseEvent event) {
    	int id = Integer.parseInt(textId.getText());
    	List<Pedido> pedidosAchados = new ArrayList<>();
    			if (id > 0) {
			try {
				Pedido p = ped.buscarPeloId(id);
				if (p != null) {
					pedidosAchados.add(p);										
					System.out.println(p.getMomento());
					} else {
					System.out.println("nao tem esse Id");
					throw new ElementDoesNotExistException(p);	
					}
				} catch (Exception e) {
				 if (e instanceof ElementDoesNotExistException) {
					 System.out.println("erro");
			      
			    	 } else {
			    	System.out.println("erro");
			    	   }
			    }
			}
		 }

     @FXML
    void buscarItem(MouseEvent event) {

    	 }


    @Override
		public void initialize(URL location, ResourceBundle resources) {
		 //TODO Auto-generated method stub
		
	}
}