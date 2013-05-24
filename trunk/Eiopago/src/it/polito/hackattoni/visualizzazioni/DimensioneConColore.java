package it.polito.hackattoni.visualizzazioni;

import java.util.Random;

import android.graphics.Color;

public class DimensioneConColore {
	private float dimensione;
	private int colore_bordo;
	private int colore_sfondo;
	public DimensioneConColore(float dimensione) {
		this.dimensione=dimensione;
		Random rnd = new Random(); 
		int a= rnd.nextInt(256);
		if(a>=240)
			a=120; //Evito il bianco
		int b= rnd.nextInt(256);
		int c = rnd.nextInt(256);
		colore_bordo = Color.argb(255, a, b, c);  
		colore_sfondo = Color.argb(201, a, b, c);
	}
	public float getDimensione() {
		return dimensione;
	}
	public int getColore_bordo() {
		return colore_bordo;
	}
	public int getColore_sfondo() {
		return colore_sfondo;
	}
}
