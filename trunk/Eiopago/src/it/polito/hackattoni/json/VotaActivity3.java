package it.polito.hackattoni.json;

import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VotaActivity3 extends Activity implements OnDownloadVotiCompleted {
	private DownloadVotiTask myDownloadVotiTask;
	private String regione;
	private TextView myTextView;
	private ProgressBar myProgressBar;
	private ListView myListView;
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
		myProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		myListView = (ListView) findViewById(R.id.listViewVota3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vota_activity3, menu);
		return true;
	}

	@Override
	public void onDownloadVotiCompleted(List<VotoConCategoria> downloadedItems,
			boolean error) {
		if (error) {
			myDownloadVotiTask
					.visualizzaDialogo(this,
							"Errore di connessione nello scaricamento del json dei voti dal server");
		} else {
			myProgressBar.setVisibility(View.GONE);
			myListView.setVisibility(View.VISIBLE);
			//disegnaGraficoQuadrato(downloadedItems);
			disegna(downloadedItems);
		}
		
	}
	
	private void disegna(List<VotoConCategoria> myList) {
		myListView.setAdapter(new MyAdapter(myList));
	}
	private class MyAdapter extends BaseAdapter {
		private List<VotoConCategoria> myAdapList;
		public MyAdapter(List<VotoConCategoria> myAdaptList) {
			this.myAdapList = myAdaptList;
		}
		@Override
		public int getCount() {
			return myAdapList.size();

		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(arg1==null) {
				arg1=new TextView(VotaActivity3.this);
			}
			((TextView) arg1).setText("Categoria: " + myAdapList.get(arg0).getCategoria() + "\nVoto medio: "+(int)myAdapList.get(arg0).getMedia());
			((TextView) arg1).setTextColor(Color.BLACK);
			((TextView) arg1).setTextSize(20);
			return arg1;
		}
		
	}
}
