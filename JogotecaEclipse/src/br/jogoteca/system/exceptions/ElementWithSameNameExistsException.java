package br.jogoteca.system.exceptions;

public class ElementWithSameNameExistsException extends Exception {

	private static final long serialVersionUID = 8248637567350230956L;
	private Object element;

	public ElementWithSameNameExistsException(String nome) {
		super("Erro: O nome \"" + nome + "\" já está sendo usado");
		this.element = nome;

	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}
