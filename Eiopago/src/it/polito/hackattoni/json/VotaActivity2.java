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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VotaActivity2 extends Activity implements OnDownloadJSONCompleted {
	private TextView myTextView;
	private String regione;
	private String categoria;
	private float voto;
	private ProgressBar myProgressBar;
	private VotaTask myVotaTask;
	private Button myButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vota_activity2);
		Intent i = getIntent();
		regione=i.getStringExtra("regione");
		categoria=i.getStringExtra("categoria");
		voto=i.getFloatExtra("voto", 3);
		
		myTextView = (TextView) findViewById(R.id.textView);
		myTextView.setText("La tua votazione è stata registrata!\n\nPremi il pulsante sottostante per visualizzare le opinioni degli altri utenti della regione "+regione);
		myProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),VotaActivity3.class);
				i.putExtra("regione", regione);
				startActivity(i);
				finish();
				
			}
		});
		myVotaTask = new VotaTask("/IoPago/Vota/user/"
				+ regione + "/" +categoria+"/"+ (int)voto, this);
		myVotaTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vota_activity2, menu);
		return true;
	}

	@Override
	public void onDownDownloadJSONCompleted(List<Item> downloadedItems,
			boolean error) {
		if (error) {
			myVotaTask
					.visualizzaDialogo(this,
							"Errore di connessione nello scaricamento del json dal server");
		} else {
			myProgressBar.setVisibility(View.GONE);
			myTextView.setVisibility(View.VISIBLE);
			//disegnaGraficoQuadrato(downloadedItems);
		}
		
	}

}
