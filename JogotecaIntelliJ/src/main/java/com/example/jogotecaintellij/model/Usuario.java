package com.example.jogotecaintellij.model;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String login;
	private String senha;

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = 1L;
	private String cpf;
	private List<ItemJogo> wishlist;
	
	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public List<ItemJogo> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<ItemJogo> wishlist) {
		this.wishlist = wishlist;
	}

	public Usuario(String cpf, String nome, String endereco, String telefone, String email, String login,
				   String senha, List<ItemJogo> wishlist) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.wishlist = wishlist;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Usuario( String nome, String endereco, String telefone, String email, String login, String senha) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public String getLogin() {
		return this.login;
	}

	public String getSenha() {
		return this.senha;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", endereco='" + endereco + '\'' +
				", telefone='" + telefone + '\'' +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", senha='" + senha + '\'' +
				", cpf='" + cpf + '\'' +
				'}';
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
