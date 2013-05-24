package it.polito.hackattoni.visualizzazioni;

import it.polito.hackattoni.eiopago.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class Legenda  {
	private List<DimensioneConColore> myListOfDimensioneConColore;
	private Context context;
	
	public Legenda(Context context, List<DimensioneConColore> myListOfDimensioneConColore) {
		this.myListOfDimensioneConColore=myListOfDimensioneConColore;
		this.context=context;
	}
	
	
	public List<TextView> getTextViewList() {
		List<TextView> tmpList = new ArrayList<TextView>();
		for (DimensioneConColore elem : myListOfDimensioneConColore) {
			TextView tmpTextView = new TextView(context);
			tmpTextView.setText(elem.getNome()+" - Soldi spesi: "+(int)(elem.getSoldi()/1000000) +" Mln €");
			tmpTextView.setTextColor(Color.BLACK);
			tmpTextView.setBackgroundResource(R.drawable.popup_transp);
			tmpTextView.setBackgroundColor(elem.getColore_bordo());
			//tmpTextView.setBackgroundResource(R.drawable.popup_transp);
			tmpList.add(tmpTextView);
		}
		return tmpList;
	}

}
