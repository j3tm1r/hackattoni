package it.polito.hackattoni.json;

import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.visualizzazioni.VistaTorta;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GraficoTortaActivity extends Activity implements
		OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private VistaTorta mTorta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grafico_torta);
		
		mTorta = (VistaTorta) findViewById(R.id.graficoTorta);

		myDownloadJSONArrayTask = new DownloadJSONArrayTask(
				"/IoPago/Regioni/2008", this);
		myDownloadJSONArrayTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
		return true;
	}

	@Override
	public void onDownDownloadJSONCompleted(List<Item> downloadedItems,
			boolean error) {
		if (error) {
			myDownloadJSONArrayTask
					.visualizzaDialogo(this,
							"Errore di connessione nello scaricamento del json dal server");
		} else {
			mTorta.setChartValues(downloadedItems);
		}
	}

}
