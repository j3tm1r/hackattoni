package it.polito.hackattoni.interfaccia;

import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import it.polito.hackattoni.json.DownloadJSONArrayTask;
import it.polito.hackattoni.json.OnDownloadJSONCompleted;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CategorieActivityV2 extends Activity implements OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private ProgressBar myProgressBar;
	private TextView myTextView;
	private ListView myListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoriev2);
		myProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
		myTextView = (TextView) findViewById(R.id.textViewAttendere);
		myListView=(ListView) findViewById(R.id.lv);
		
		//Istanzio il nuovo thread che recupera il file json
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Categorie/2008", this);
		myDownloadJSONArrayTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categorie_activity_v2, menu);
		return true;
	}

	@Override
	public void onDownDownloadJSONCompleted(List<Item> downloadedItems,	boolean error) {
		if(error) {
			myDownloadJSONArrayTask.visualizzaDialogo(this, "Errore di connessione nello scaricamento del json dal server");
		}
		else {
			//Nascondo la progress bar e disegno il grafico
			myProgressBar.setVisibility(View.GONE);
			myTextView.setVisibility(View.GONE);
			myListView.setVisibility(View.VISIBLE);
			disegnaGrafico(downloadedItems);
		}
		
	}
	
	public float dimensions(){
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		//return (int) (metrics.heightPixels/metrics.ydpi);
		return metrics.density;
	}
	
	private void disegnaGrafico(List<Item> downloadedItems) {
		//DA ELIMINARE TUTTO QUI SOTTO:
		
		/*myTextView.setVisibility(View.VISIBLE);
		for (Item item : downloadedItems) {
			myTextView.setText("\n"+myTextView.getText()+" "+item.getAnno()+" "+item.getCategoria()+" "+item.getRegione()+ " "+ item.getSpesa());
			
		}*/
		
		//myListView.setAdapter(new CategorieAdapter(new int[]{33, 50, 99, 20}));
		//
		
		myListView.setAdapter(new CategorieAdapter(downloadedItems, dimensions()));
		myListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				String cName= ((Categoria) myListView.getItemAtPosition(arg2)).getName();
				//Intent newActivity1 = new Intent(CategorieActivityV2.this, NewActivity1.class);     
	            //startActivity(newActivity1, cName);
				
			}});
		System.out.println("AREA: "+dimensions());
		
		//DEBUG:
		myTextView.setVisibility(View.VISIBLE);
		myTextView.setText("Area: "+dimensions());
		//FINE DEBUG
	}

}
