package br.jogoteca.system.models;

public class User {
    
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String login;
    private String senha;

    public User(int id, String nome, String endereco, String telefone, String email, String login, String senha) {
        this.id = id;
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
		return "User [Id = " + id + ", Nome = " + nome + ", Endereço = " + endereco + ", Telefone = " + telefone + ", E-mail = "
				+ email + ", Login = " + login + ", Senha = " + senha + "]";
	}
    
    
}
