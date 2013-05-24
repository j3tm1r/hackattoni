package it.polito.hackattoni.interfaccia;

import it.polito.hackattoni.eiopago.Item;

import java.util.List;

import it.polito.hackattoni.eiopago.R;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RegioniAdapter extends BaseAdapter {
	
	
	private List<Item> regioni;
	private double max;
	private float dimensions;
	
	
	public RegioniAdapter(List<Item> regioni, float dimensions){
		this.regioni=regioni;
		this.dimensions=dimensions;
		max=0;
		for (Item item : regioni) {
			if(item.getSpesa()>max){
				max=item.getSpesa();
			}
		}
		
	}

	@Override
	public int getCount() {
		
		return regioni.size();
	}

	@Override
	public Object getItem(int i) {
		
		return regioni.get(i);
	}

	@Override
	public long getItemId(int i) {
		
		return i;
	}
	
	public int percent(double value){

		return (int) ((value/max)*79)+21;
		
	}
	

	
	

	@Override
	public View getView(int i, View v, ViewGroup vg) {
		System.out.println("getting view #"+i);
		if(v==null){
			v=new Regione(vg.getContext());
		}
		Regione r=(Regione) v;
		//int color=Color.HSVToColor(255, new float[]{0f,((getCount()-i)*1.0f/getCount()),1.0f});
		int color=Color.HSVToColor(255, new float[]{230f,(percent(regioni.get(i).getSpesa()))/100.0f,1.0f});
		r.setDim(dimensions);
		r.setColor(color);
		r.setLenght(percent(regioni.get(i).getSpesa()));
		//c.setLenght((i+1)*10);
		r.setPadding(10, 10, 10, 0);
		r.setName(regioni.get(i).getRegione());
		r.setImage(R.drawable.regione);
		
		System.out.println("lunghezza= "+r.getLenght());
		
		
		return r;
	}

}