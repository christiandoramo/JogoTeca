package com.example.jogotecaintellij.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private LocalDate releaseDate;
	private Genre genre;
	private String description;
	private String publicadora;
	private String desenvolvedora;
	private Double price;
	private String imageURL;
	private String videoUrl;
	private List<String> imagesUrl;

//	(int id, String name, LocalDate releaseDate, String description,  String publicadora, String desenvolvedora,String imageURL, String videoUrl,
//	List<String> imagesUrl, Genre genre,Double price);


	public Game(int id, String name, LocalDate releaseDate, Genre genre, String description, String publicadora, String desenvolvedora, Double price, String imageURL, String videoUrl, List<String> imagesUrl) {
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.description = description;
		this.publicadora = publicadora;
		this.desenvolvedora = desenvolvedora;
		this.price = price;
		this.imageURL = imageURL;
		this.videoUrl = videoUrl;
		this.imagesUrl = imagesUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public List<String> getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(List<String> imagesUrl) {
		this.imagesUrl = imagesUrl;
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
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
