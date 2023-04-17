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
    private String profileUrl;

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final long serialVersionUID = 1L;
    private String cpf;
    private List<ItemJogo> wishlist;


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
        profileUrl = "src/main/resources/com/example/jogotecaintellij/view/midias/perfil300x300.png";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Usuario(String nome, String endereco, String telefone, String email, String login, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.login = login;
        this.senha = senha;
        profileUrl = "src/main/resources/com/example/jogotecaintellij/view/midias/perfil300x300.png";
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ItemJogo> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<ItemJogo> wishlist) {
        this.wishlist = wishlist;
    }
}
