package br.jogoteca.system.models;

public class Usuario {

	 
	    private String id;
	    private String pedido;
	    

	    public Usuario(String id, String pedido) {  
	        this.id = id;
	        this.pedido = pedido;
	    }
	    
		public String getId() {
	    	return id;
	    }
	    
	   

		public void setId( String id) {
			this.id = id;
			
		}

		public void setPedido(String pedido) {
			this.pedido = pedido;;
			
		}



		public String getPedido() {
			
				return pedido;
		}
}


