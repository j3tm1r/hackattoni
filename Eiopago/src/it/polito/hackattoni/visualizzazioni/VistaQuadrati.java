package it.polito.hackattoni.visualizzazioni;

import java.util.ArrayList;
import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.view.View;

public class VistaQuadrati extends View {
	private List<Item> myList;
	private int screenWidth;
	private int screenHeight;
	private Paint background;
	private Paint foreground;
	private Path borderRectangle = new Path();
	private RectF myRect = new RectF();
	private List<DimensioneConColore> myListOfLati;

	public List<DimensioneConColore> getMyListOfLati() {
		return myListOfLati;
	}



	public VistaQuadrati(Context context, List<Item> iList, int iScreenWidth, int iScreenHeight) {
		super(context);
		this.myList = iList;
		this.screenWidth=iScreenWidth-40; //tolgo 40 pixel per tenermi largo
		this.screenHeight=iScreenHeight-40; //lascio un bordino sotto
		myListOfLati = new ArrayList<DimensioneConColore>();
		inizializza();
	}



	@Override
	protected void onDraw(Canvas canvas) {

		
		
		
		canvas.save();

		canvas.translate(2, 0); //Bordino a sinistra
		for (DimensioneConColore elem : myListOfLati) {
			//Setto il colore:
			background.setColor(elem.getColore_sfondo());
			foreground.setColor(elem.getColore_bordo());
			
			borderRectangle.reset();
			float altezzaRettangolo=elem.getDimensione();
			if (altezzaRettangolo>screenHeight*2/3)
				altezzaRettangolo=screenHeight*2/3; //taglio l'altezza del rettangolo se supera di due terzi lo schermo
			canvas.translate((float)0, screenHeight-altezzaRettangolo); //porto giu
			myRect.set(0, 0, elem.getDimensione(), altezzaRettangolo);
			borderRectangle.addRoundRect(myRect, 5, 5, Path.Direction.CW);
			canvas.drawPath(borderRectangle, background); //bordi rettangolo
			canvas.drawPath(borderRectangle, foreground); //sfondo rettangolo
			canvas.translate(elem.getDimensione()+1, (float)0); //mi sposto per il successivo rettangolo
			canvas.translate((float)0, -screenHeight+altezzaRettangolo); //rimetto su
		}
		

		canvas.restore();

	}
	
	private void inizializza() {

		background = new Paint();
		foreground = new Paint();
		//colore sfondo rettangolo:
		//int backgroundColor = 0xc99ac95c;
		//colore bordo (rettangolo e freccia):
		//int borderColor = 0xff518a22;
		//Paint per lo sfondo del rettangolo:
		//background.setColor(backgroundColor);
		background.setAntiAlias(true);
		background.setStyle(Style.FILL);
		
		//Paint per i bordi (di rettangolo e freccia):
		//foreground.setColor(borderColor);
		foreground.setAntiAlias(true);
		foreground.setStrokeWidth(2.f);
		foreground.setStyle(Style.STROKE);
		
		//Mi creo una lista dei lati:
		//Cerco il lato massimo:
		double sommaSpese=0;
		for (Item myItem : myList) {
				sommaSpese+=myItem.getSpesa();
		}
		//double lato = spesa*width/max;
		for (Item myItem : myList) {
			int dimensione = (int)(myItem.getSpesa()*screenWidth/sommaSpese);
			String nome = myItem.getCategoria();
			myListOfLati.add(new DimensioneConColore(dimensione,nome,myItem.getSpesa()));
		}

	}
}
