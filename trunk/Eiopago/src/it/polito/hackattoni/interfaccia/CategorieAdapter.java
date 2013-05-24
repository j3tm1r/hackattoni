package it.polito.hackattoni.interfaccia;

import it.polito.hackattoni.eiopago.Item;

import java.util.ArrayList;
import java.util.List;

import it.polito.hackattoni.eiopago.R;

import it.polito.hackattoni.json.GraficoTortaActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CategorieAdapter extends BaseAdapter {

	private List<Item> categorie;
	private double max;
	private float dimensions;
	private List<Bitmap> icone;
	private Context mContext;

	public CategorieAdapter(Resources resources, List<Item> categorie,
			float dimensions, Context context) {
		this.mContext = context;
		this.categorie = categorie;
		this.dimensions = dimensions;
		max = 0;
		icone = new ArrayList<Bitmap>();
		for (Item item : categorie) {
			if (item.getSpesa() > max) {
				max = item.getSpesa();
			}
		}

		for (int i = 0; i < categorie.size(); i++) {
			icone.add(BitmapFactory.decodeResource(resources, matchID(i)));
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

	public int percent(double value) {

		return (int) ((value / max) * 79) + 21;

	}

	public int matchID(int i) {

		if (categorie.get(i).getCategoria().equalsIgnoreCase("Sanita"))
			return R.drawable.sanita;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Agricoltura"))
			return R.drawable.agricoltura;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Oneri non ripartibili"))
			return R.drawable.autonomie;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Cultura e servizi ricreativi"))
			return R.drawable.cultura;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Amministrazione Generale"))
			return R.drawable.debito;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Difesa"))
			return R.drawable.difesa;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Giustizia"))
			return R.drawable.giustizia;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Energia"))
			return R.drawable.industria;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Istruzione"))
			return R.drawable.istruzione;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Previdenza"))
			return R.drawable.previdenza;
		if (categorie.get(i).getCategoria().equalsIgnoreCase("Ricerca"))
			return R.drawable.ricerca;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Smaltimento dei Rifiuti"))
			return R.drawable.rifiuti;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Sicurezza pubblica"))
			return R.drawable.sicurezza;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Investimenti in campo sociale"))
			return R.drawable.sociale;
		if (categorie.get(i).getCategoria()
				.equalsIgnoreCase("Sviluppo economico"))
			return R.drawable.sviluppoeco;

		else
			return 0;

	}

	@Override
	public View getView(int i, View v, ViewGroup vg) {
		System.out.println("getting view #" + i);
		if (v == null) {
			v = new Categoria(vg.getContext());
		}
		Categoria c = (Categoria) v;
		// int color=Color.HSVToColor(255, new
		// float[]{0f,((getCount()-i)*1.0f/getCount()),1.0f});
		int color = Color.HSVToColor(255, new float[] { 0f,
				(percent(categorie.get(i).getSpesa())) / 100.0f, 1.0f });

		c.setDim(dimensions);
		c.setColor(color);
		c.setLenght(percent(categorie.get(i).getSpesa()));
		// c.setLenght((i+1)*10);
		c.setPadding(10, 10, 10, 0);
		c.setName(categorie.get(i).getCategoria());

		c.setImage(icone.get(i));
		System.out.println("lunghezza= " + c.getLenght());

		v.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String categoriaName = ((Categoria) v).getName();
				Intent i = new Intent(mContext, GraficoTortaActivity.class);
				i.putExtra("categoria", categoriaName);
				mContext.startActivity(i);
			}
		});

		return c;
	}
}
