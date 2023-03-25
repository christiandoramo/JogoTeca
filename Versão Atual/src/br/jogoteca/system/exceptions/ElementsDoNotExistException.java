package br.jogoteca.system.exceptions;

import java.util.List;

import br.jogoteca.system.models.Game;

public class ElementsDoNotExistException extends Exception {

	private static final long serialVersionUID = 8248637567350230956L;
	private Object element;

	/*
	 * public ElementsDoNotExistException(List<?> obj) { super("Erro: Nenhum " +
	 * obj.get(0).getClass().getSimpleName() + " foi encontrado"); this.element =
	 * obj; }
	 * Na verdade, se a lista estiver vazia, não há como saber qual é o tipo do
	 * objeto que deveria ser armazenado na lista. Isso ocorre porque o tipo
	 * genérico da lista, definido entre os sinais de maior e menor (<>), é apagado
	 * em tempo de execução, devido ao recurso chamado de "apagamento de tipo" (type
	 * erasure) em Java. Isso significa que a informação sobre o tipo genérico da
	 * lista não está disponível em tempo de execução e, portanto, não é possível
	 * verificar o tipo dos elementos armazenados na lista, se a lista estiver
	 * vazia. Nesse caso, é comum que as bibliotecas e frameworks Java que utilizam
	 * listas vazias geralmente retornem um valor padrão, ou lançam uma exceção
	 * informando que a lista está vazia e que não há elementos para processar.
	 * 
	 */

	public ElementsDoNotExistException(List<T> obj) {
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
