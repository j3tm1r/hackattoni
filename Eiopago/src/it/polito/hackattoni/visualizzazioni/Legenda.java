package it.polito.hackattoni.visualizzazioni;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
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
			tmpTextView.setText(elem.getNome()+" - Soldi spesi: "+(int)elem.getSoldi()/1000000 +" Mln €");
			tmpTextView.setTextColor(elem.getColore_bordo());
			tmpList.add(tmpTextView);
		}
		return tmpList;
	}

}
