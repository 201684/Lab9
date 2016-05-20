package it.polito.tdp.porto.model;

public class Autorship {
	
	private int idAutorship;
	private int idArticle;
	private int idCreator;
	
	public Autorship(int idAutorship, int idArticle, int idCreator) {
		super();
		this.idAutorship = idAutorship;
		this.idArticle = idArticle;
		this.idCreator = idCreator;
	}

	public int getIdAutorship() {
		return idAutorship;
	}

	public void setIdAutorship(int idAutorship) {
		this.idAutorship = idAutorship;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public int getIdCreator() {
		return idCreator;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAutorship;
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
		Autorship other = (Autorship) obj;
		if (idAutorship != other.idAutorship)
			return false;
		return true;
	}
	
	

}
