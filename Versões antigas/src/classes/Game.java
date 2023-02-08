package projeto2va;

import java.util.Objects;

public class Game {
	private String nome;
	private String codigo;
	private String produtora;
	private String ano;
	private String publicadora;
	private float receita;
	private Estado estado;
	private Genero genero;

	public Game(String codigo, String nome, String produtora, String ano, String publicadora, Genero genero, Estado estado) {
		this.nome = nome;
		this.codigo = codigo;
		this.produtora = produtora;
		this.ano = ano;
		this.publicadora = publicadora;
		this.genero = genero;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public float getReceita() {
		return receita;
	}

	public void setReceita(float receita) {
		this.receita = receita;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getProdutora() {
		return produtora;
	}

	public String getAno() {
		return ano;
	}

	public String getPublicadora() {
		return publicadora;
	}

	public Genero getGenero() {
		return genero;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setPublicadora(String publicadora) {
		this.publicadora = publicadora;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return " Codigo: " + codigo + " Nome: " + nome + "Genero: " + genero + " Produtora: " + produtora + " Ano: "
				+ ano + " Publicadora: " + publicadora + "Estado: " + estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
