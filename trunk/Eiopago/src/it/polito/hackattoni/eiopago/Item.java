package it.polito.hackattoni.eiopago;

public class Item {
	private String regione;
	private String categoria;
	private double spesa;
	private int abitanti;

	private int anno;

	public Item(String regione, String categoria, double spesa, int anno,
			int abitanti) {
		this.regione = regione;
		this.categoria = categoria;
		this.spesa = spesa;
		this.anno = anno;
		this.abitanti = abitanti;
	}

	public int getAnno() {
		return anno;
	}

	public String getRegione() {
		return regione;
	}

	public String getCategoria() {
		return categoria;
	}

	public double getSpesa() {
		return spesa;
	}

	public int getAbitanti() {
		return abitanti;
	}
}
