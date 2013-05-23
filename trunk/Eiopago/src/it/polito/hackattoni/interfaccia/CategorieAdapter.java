package it.polito.hackattoni.interfaccia;

import it.polito.hackattoni.eiopago.Item;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CategorieAdapter extends BaseAdapter {
	
	private int[] lunghezze;
	private List<Item> categorie;
	

	public CategorieAdapter(int[] lunghezze){
		this.lunghezze=lunghezze;
	}
	
	public CategorieAdapter(List<Item> categorie){
		this.categorie=categorie;
	}

	@Override
	public int getCount() {
		
		return lunghezze.length;
	}

	@Override
	public Object getItem(int i) {
		
		return lunghezze[i];
	}

	@Override
	public long getItemId(int i) {
		
		return i;
	}

	@Override
	public View getView(int i, View v, ViewGroup vg) {
		System.out.println("getting view #"+i);
		if(v==null){
			v=new Categoria(vg.getContext());
		}
		Categoria c=(Categoria) v;
		int color=Color.HSVToColor(255, new float[]{0f,((getCount()-i)*1.0f/getCount()),1.0f});
		c.setColor(color);
		c.setLenght(lunghezze[i]);
		c.setPadding(10, 10, 10, 10);
		//c.setName(categorie.get(i).getCategoria());
		
		return c;
	}

}
