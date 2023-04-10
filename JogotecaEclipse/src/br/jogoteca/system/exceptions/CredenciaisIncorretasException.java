package br.jogoteca.system.exceptions;


public class CredenciaisIncorretasException extends Exception {
	private static final long serialVersionUID = -7096022758377417172L;
	public CredenciaisIncorretasException() {
		super("Usuário ou senha inválidos");
	}
}
