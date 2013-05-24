package it.polito.hackattoni.proposte;

import java.sql.Timestamp;

public class Proposta {
	private String oggetto, proposta, utente, regione;
	private int id_proposta, voti;
	private java.sql.Timestamp timestamp;

	public Proposta(String oggetto, String proposta, String utente,
			String regione, int id, int voti) {

		this.oggetto = oggetto;
		this.proposta = proposta;
		this.utente = utente;
		this.regione = regione;
		this.id_proposta = id;
		this.voti = voti;

	}

	public String getOggetto() {
		return oggetto;
	}

	public String getProposta() {
		return proposta;
	}

	public String getUtente() {
		return utente;
	}

	public String getRegione() {
		return regione;
	}

	public int getId_proposta() {
		return id_proposta;
	}

	public int getVoti() {
		return voti;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
}