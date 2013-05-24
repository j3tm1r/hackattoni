package it.polito.hackattoni.json;

public class VotoConCategoria {
	public String getCategoria() {
		return categoria;
	}

	public double getMedia() {
		return media;
	}

	private String categoria;
	private double media;
	
	public VotoConCategoria(String categoria, double media) {
		this.categoria=categoria;
		this.media=media;
	}
}
