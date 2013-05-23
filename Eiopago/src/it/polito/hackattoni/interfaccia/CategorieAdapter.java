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
	private double max;
	

	public CategorieAdapter(int[] lunghezze){
		this.lunghezze=lunghezze;
	}
	
	public CategorieAdapter(List<Item> categorie){
		this.categorie=categorie;
		max=0;
		for (Item item : categorie) {
			if(item.getSpesa()>max){
				max=item.getSpesa();
			}
		}
		
	}

	@Override
	public int getCount() {
		
		return categorie.size();
	}

	@Override
	public Object getItem(int i) {
		
		return categorie.get(i);
	}

	@Override
	public long getItemId(int i) {
		
		return i;
	}
	
	public int percent(double value){

		return (int) (value/max*100);
		
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
		c.setLenght(percent(categorie.get(i).getSpesa()));
		c.setPadding(10, 10, 10, 10);
		c.setName(categorie.get(i).getCategoria());
		
		return c;
	}

}
