package it.polito.hackattoni.interfaccia;

import it.polito.hackattoni.eiopago.Item;

import java.util.List;

import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.json.GraficoQuadratiActivity;
import it.polito.hackattoni.json.GraficoTortaActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

public class RegioniAdapter extends BaseAdapter {
	
	
	private List<Item> regioni;
	private double max;
	private float dimensions;
	private Context mContext;
	
	public RegioniAdapter(List<Item> regioni, float dimensions, Context context){
		this.regioni=regioni;
		this.dimensions=dimensions;
		max=0;
		this.mContext=context;
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
		final Regione r=(Regione) v;
		//int color=Color.HSVToColor(255, new float[]{0f,((getCount()-i)*1.0f/getCount()),1.0f});
		int color=Color.HSVToColor(255, new float[]{230f,(percent(regioni.get(i).getSpesa()))/100.0f,1.0f});
		r.setDim(dimensions);
		r.setColor(color);
		r.setLenght(percent(regioni.get(i).getSpesa()));
		//c.setLenght((i+1)*10);
		r.setPadding(10, 10, 10, 0);
		r.setName(regioni.get(i).getRegione());
		r.setImage(R.drawable.regione);
		
		//System.out.println("lunghezza= "+r.getLenght());
		r.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String regioneName = ((Regione) r).getName();
				Intent i = new Intent(mContext, GraficoQuadratiActivity.class);
				i.putExtra("regione", regioneName);
				i.putExtra("anno", 2008);
				mContext.startActivity(i);
			}
		});
		
		return r;
	}

}