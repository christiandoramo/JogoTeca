package com.example.jogotecaintellij.model;


import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.enums.StatusItemJogo;

import java.io.Serializable;
import java.time.LocalDate;

public class ItemJogo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    // APENAS PARA SALVAR OS DADOS DE JOGO EM CASO DE REMOÇÃO
    private int idJogo;
    private String name;
    private LocalDate releaseDate;
    private Genre genre;
    private String description;
    private String publicadora;
    private String desenvolvedora;
    private Double price;
    private String imageURL;
    private String videoUrl;
    // APENAS PARA SALVAR OS DADOS DE JOGO EM CASO DE REMOÇÃO
    //////////////////////////////////////////////////////////////////////////////////
    private Jogo game;
    private StatusItemJogo status;

    public ItemJogo(Jogo game) {
        idJogo = game.getId();
        name = game.getName();
        releaseDate = game.getReleaseDate();
        genre = game.getGenre();
        description = game.getDescription();
        publicadora = game.getPublicadora();
        desenvolvedora = game.getDesenvolvedora();
        price = game.getPrice();
        imageURL = game.getImageURL();
        videoUrl = game.getVideoUrl();
        this.game = game;
        status = StatusItemJogo.DISPONIVEL;
    }

    public ItemJogo(double price, Jogo game) {
        super();
        this.price = price;

        idJogo = game.getId();
        name = game.getName();
        releaseDate = game.getReleaseDate();
        genre = game.getGenre();
        description = game.getDescription();
        publicadora = game.getPublicadora();
        desenvolvedora = game.getDesenvolvedora();
        imageURL = game.getImageURL();
        videoUrl = game.getVideoUrl();
        this.game = game;
        status = StatusItemJogo.DISPONIVEL;
    }

    // caso crie um jogo indisponivel
    public ItemJogo(double price, Jogo game, StatusItemJogo status) {
        this.price = price;
        this.status = status;

        idJogo = game.getId();
        name = game.getName();
        releaseDate = game.getReleaseDate();
        genre = game.getGenre();
        description = game.getDescription();
        publicadora = game.getPublicadora();
        desenvolvedora = game.getDesenvolvedora();
        imageURL = game.getImageURL();
        videoUrl = game.getVideoUrl();
        this.game = game;
    }

    public StatusItemJogo getStatus() {
        return status;
    }

    public void setStatus(StatusItemJogo status) {
        this.status = status;
    }

    public Jogo getGame() {
        return game;
    }

    public void setGame(Jogo game) {
        this.game = game;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /////////////////////////////////////////DADOS/////////////////////////////////////////DADOS
    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicadora() {
        return publicadora;
    }

    public void setPublicadora(String publicadora) {
        this.publicadora = publicadora;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /////////////////////////////////////////DADOS/////////////////////////////////////////DADOS
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
        ItemJogo other = (ItemJogo) obj;
        if (id != other.id)
            return false;
        return true;
    }

}