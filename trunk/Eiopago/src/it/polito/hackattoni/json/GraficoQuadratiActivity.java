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
import android.widget.FrameLayout;
import android.widget.TextView;

public class GraficoQuadratiActivity extends Activity implements OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private TextView myTextView;
	private FrameLayout myFrameLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grafico_quadrati);
		myTextView = (TextView) findViewById(R.id.textView);
		myFrameLayout = (FrameLayout) findViewById(R.id.frame_layout_grafico_quadrati);
		
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Regioni/piemonte/2008", this);
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
			disegnaGraficoQuadrato(downloadedItems);
		}
	}
	
	private void disegnaGraficoQuadrato(List<Item> iList) {
		int width = myFrameLayout.getWidth();
		int height = myFrameLayout.getHeight();
		myFrameLayout.addView(new VistaQuadrati(this, iList, width, height));
		
	}

}