package br.jogoteca.system.exceptions;

public class ElementAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 4868332235826988515L;
	
	private Object element;
    
    public ElementAlreadyExistsException(Object obj) {
        super("Erro: Objeto não pode ser adicionado, pois ja está cadastrado no repositorio");
        this.element = obj;        
    }
    
    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
    
}
