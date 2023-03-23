//package br.jogoteca.system.models;
//
//import java.time.LocalDate;
//
//public class Order {
//	private long id;
//	private String name;
//	private LocalDate releaseDate;
//	private String description;
//	private String imageURL;
//	private Double price;
//	private Genre genre;
//	
//	
//	public Genre getGenre() {
//		return genre;
//	}
//	public void setGenre(Genre genre) {
//		this.genre = genre;
//	}
//	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public LocalDate getReleaseDate() {
//		return releaseDate;
//	}
//	public void setReleaseDate(LocalDate releaseDate) {
//		this.releaseDate = releaseDate;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getImageURL() {
//		return imageURL;
//	}
//	public void setImageURL(String imageURL) {
//		this.imageURL = imageURL;
//	}
//	public Double getPrice() {
//		return price;
//	}
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//
//	public Game(long id, String name, LocalDate releaseDate, Genre genre, String description, String imageURL, Double price) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.releaseDate = releaseDate;
//		this.description = description;
//		this.imageURL = imageURL;
//		this.price = price;
//		this.genre = genre;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (id ^ (id >>> 32));
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Game other = (Game) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
//}
