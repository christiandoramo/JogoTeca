package projeto2va;

import java.util.Objects;

public class RegistroGame {
	private int id;
	private Game game;
	private User usuario = null;
	private float preco;

	public RegistroGame(int id, Game game, float preco) {
		this.id = id;
		this.game = game;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return game + " preco: " + preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroGame other = (RegistroGame) obj;
		return Objects.equals(game.getNome(), other.game.getNome());
	}

}