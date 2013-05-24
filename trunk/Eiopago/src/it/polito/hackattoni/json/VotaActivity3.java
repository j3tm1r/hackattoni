package it.polito.hackattoni.json;

import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VotaActivity3 extends Activity implements OnDownloadJSONCompleted {
	private DownloadVotiTask myDownloadVotiTask;
	private String regione;
	private TextView myTextView;
	private ProgressBar myProgressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vota_activity3);
		Intent i = getIntent();
		regione=i.getStringExtra("regione");
		myTextView = (TextView) findViewById(R.id.textView1);
		myDownloadVotiTask = new DownloadVotiTask("/IoPago/Voti/"
				+ regione, this);
		myDownloadVotiTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vota_activity3, menu);
		return true;
	}

	@Override
	public void onDownDownloadJSONCompleted(List<Item> downloadedItems,
			boolean error) {
		if (error) {
			myDownloadVotiTask
					.visualizzaDialogo(this,
							"Errore di connessione nello scaricamento del json dal server");
		} else {
			myProgressBar.setVisibility(View.GONE);
			//disegnaGraficoQuadrato(downloadedItems);
		}
		
	}

}
