package com.example.jogotecaintellij.exception;

import java.util.List;

public class ElementsDoNotExistException extends Exception {

	private static final long serialVersionUID = 8248637567350230956L;
	private Object element;

	/*
	 * public ElementsDoNotExistException(List<?> obj) { super("Erro: Nenhum " +
	 * obj.get(0).getClass().getSimpleName() + " foi encontrado"); this.element =
	 * obj; }
	 * Na verdade, se a lista estiver vazia, n�o h� como saber qual � o tipo do
	 * objeto que deveria ser armazenado na lista. Isso ocorre porque o tipo
	 * gen�rico da lista, definido entre os sinais de maior e menor (<>), � apagado
	 * em tempo de execu��o, devido ao recurso chamado de "apagamento de tipo" (type
	 * erasure) em Java. Isso significa que a informa��o sobre o tipo gen�rico da
	 * lista n�o est� dispon�vel em tempo de execu��o e, portanto, n�o � poss�vel
	 * verificar o tipo dos elementos armazenados na lista, se a lista estiver
	 * vazia. Nesse caso, � comum que as bibliotecas e frameworks Java que utilizam
	 * listas vazias geralmente retornem um valor padr�o, ou lan�am uma exce��o
	 * informando que a lista est� vazia e que n�o h� elementos para processar.
	 * 
	 */

	public ElementsDoNotExistException(List<?> obj) {
		super("Erro: Nenhum foi encontrado");
		this.element = obj;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}
