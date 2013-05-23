package it.polito.hackattoni.json;

import java.util.List;

import org.json.JSONArray;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class JsonActivity extends Activity implements OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private TextView myTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		myTextView = (TextView) findViewById(R.id.textViewJson);
		
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Categorie", this);
		myDownloadJSONArrayTask.execute();
;		
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
			for (Item item : downloadedItems) {
				myTextView.setText("\n"+myTextView.getText()+" "+item.getAnno()+" "+item.getCategoria()+" "+item.getRegione()+ " "+ item.getSpesa());
				
			}
		}
	}

}
