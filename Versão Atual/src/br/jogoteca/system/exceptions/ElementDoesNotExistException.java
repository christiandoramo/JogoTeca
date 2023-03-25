package br.jogoteca.system.exceptions;

public class ElementDoesNotExistException extends Exception {
	private static final long serialVersionUID = -7096022758377417172L;

	private Object element;

	public ElementDoesNotExistException(Object obj) {
		super("Erro: " + obj.getClass().getSimpleName() + " não foi encontrado");
		this.element = obj;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

}
