package br.jogoteca.system.exceptions;

public class ElementWithSameCPFExistsException extends Exception {

	private static final long serialVersionUID = 2797819599537336385L;
	private Object element;

	public ElementWithSameCPFExistsException(String cpf) {
		super("Erro: O cpf \"" + cpf + "\" já está sendo usado");
		this.element = cpf;

	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}
