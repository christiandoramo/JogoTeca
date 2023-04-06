package com.example.jogotecaintellij.exception;

public class ElementWithSameCPFExistsException extends Exception {

	private static final long serialVersionUID = 2797819599537336385L;
	private Object element;

	public ElementWithSameCPFExistsException(String cpf) {
		super("Erro: O cpf \"" + cpf + "\" j� est� sendo usado");
		this.element = cpf;

	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}
