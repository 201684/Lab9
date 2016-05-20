package it.polito.tdp.porto.model;

public class Article {
	
	private int idArticle;
	private int year;
	
	@Override
	public String toString() {
		return idArticle + " " + year + " " + title;
	}

	private String title;
	
	public Article(int idArticle, int year, String title) {
		super();
		this.idArticle = idArticle;
		this.year = year;
		this.title = title;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArticle;
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
		Article other = (Article) obj;
		if (idArticle != other.idArticle)
			return false;
		return true;
	}
	
	

}
