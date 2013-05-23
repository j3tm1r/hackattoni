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
import android.view.Menu;
import android.view.View;
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
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Categorie", this);
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
	
	
	
	private void disegnaGrafico(List<Item> downloadedItems) {
		//DA ELIMINARE TUTTO QUI SOTTO:
		
		/*myTextView.setVisibility(View.VISIBLE);
		for (Item item : downloadedItems) {
			myTextView.setText("\n"+myTextView.getText()+" "+item.getAnno()+" "+item.getCategoria()+" "+item.getRegione()+ " "+ item.getSpesa());
			
		}*/
		
		myListView.setAdapter(new CategorieAdapter(new int[]{33, 50, 99, 20}));
	}

}
