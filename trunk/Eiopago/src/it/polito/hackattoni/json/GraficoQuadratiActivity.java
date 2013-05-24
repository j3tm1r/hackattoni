package it.polito.hackattoni.json;

import java.util.List;

import org.json.JSONArray;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import it.polito.hackattoni.visualizzazioni.VistaQuadrati;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GraficoQuadratiActivity extends Activity implements OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private TextView myTextView;
	private FrameLayout myFrameLayout;
	private String regione;
	private ProgressBar myProgressBar;
	private int anno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Recupero l'intent
		regione=getIntent().getStringExtra("regione");
		anno=getIntent().getIntExtra("anno", 2008);
		
		setContentView(R.layout.activity_grafico_quadrati);
		myTextView = (TextView) findViewById(R.id.textView);
		myTextView.setText("Regione: "+regione+"\n"+"Anno: "+anno);
		myFrameLayout = (FrameLayout) findViewById(R.id.frame_layout_grafico_quadrati);
		myProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Regioni/"+regione+"/"+anno, this);
		myDownloadJSONArrayTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
		return true;
	}

	@Override
	public void onDownDownloadJSONCompleted(List<Item> downloadedItems, boolean error) {
		if(error) {
			myDownloadJSONArrayTask.visualizzaDialogo(this, "Errore di connessione nello scaricamento del json dal server");
		}
		else {		
			myProgressBar.setVisibility(View.GONE);
			disegnaGraficoQuadrato(downloadedItems);
		}
	}
	
	private void disegnaGraficoQuadrato(List<Item> iList) {
		/*
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels;
		*/
		int width = myFrameLayout.getWidth();
		int height = myFrameLayout.getHeight();

		myFrameLayout.addView(new VistaQuadrati(this, iList, width, height));

		
	}

}