package it.polito.hackattoni.json;

import java.util.ArrayList;
import java.util.List;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.visualizzazioni.VistaTorta;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class GraficoTortaActivity extends Activity implements
		OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private VistaTorta mTorta;
	private ToggleButton mSpesaPro;
	private List<Item> itemPro, itemAll;
	private boolean procapite = false, allDown = false, proCapiteDown = false;
	private Spinner mSpinner;
	private int anno = 1996;
	private String categoria;
	private SeekBar mSeekBar;
	private TextView mTextAnno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grafico_torta);

		categoria = this.getIntent().getExtras().getString("categoria");

		mTorta = (VistaTorta) findViewById(R.id.graficoTorta);

		mTextAnno = (TextView) findViewById(R.id.textAnno);
		// mSpinner = (Spinner) findViewById(R.id.spinner1);
		//
		// // Create an ArrayAdapter using the string array and a default
		// spinner
		// // layout
		// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		// this, R.array.anni_spinner,
		// android.R.layout.simple_spinner_item);
		// // Specify the layout to use when the list of choices appears
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// // Apply the adapter to the spinner
		//
		// mSpinner.setAdapter(adapter);
		// mSpinner.setSelection(2008 - 1996);
		//
		// mSpinner.setOnItemSelectedListener(new
		// AdapterView.OnItemSelectedListener() {
		//
		// @Override
		// public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
		// long arg3) {
		// if (mSpesaPro.isChecked())
		// mSpesaPro.toggle();
		// procapite = false;
		// myDownloadJSONArrayTask = new DownloadJSONArrayTask(
		// "/IoPago/Categorie/" + categoria.replace(" ", "%20")
		// + "/" + (1996 + pos), GraficoTortaActivity.this);
		// myDownloadJSONArrayTask.execute();
		// anno = 1996 + pos;
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> arg0) {
		//
		// }
		// });

		mSeekBar = (SeekBar) findViewById(R.id.cambiaAnno);

		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				if (mSpesaPro.isChecked())
					mSpesaPro.toggle();
				procapite = false;
				myDownloadJSONArrayTask = new DownloadJSONArrayTask(
						"/IoPago/Categorie/" + categoria.replace(" ", "%20")
								+ "/" + (1996 + arg1),
						GraficoTortaActivity.this);
				myDownloadJSONArrayTask.execute();
				anno = 1996 + arg1;
				mTextAnno.setText("Anno : " + anno);
			}
		});

		mSpesaPro = (ToggleButton) findViewById(R.id.spesaProCapite);
		mSpesaPro.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					procapite = true;
					myDownloadJSONArrayTask = new DownloadJSONArrayTask(
							"/IoPago/Categorie/"
									+ categoria.replace(" ", "%20") + "/"
									+ anno, GraficoTortaActivity.this);
					myDownloadJSONArrayTask.execute();
				} else {
					procapite = false;
					myDownloadJSONArrayTask = new DownloadJSONArrayTask(
							"/IoPago/Categorie/"
									+ categoria.replace(" ", "%20") + "/"
									+ anno, GraficoTortaActivity.this);
					myDownloadJSONArrayTask.execute();
				}

			}
		});

		mTextAnno.setText("Anno : " + anno);

		myDownloadJSONArrayTask = new DownloadJSONArrayTask(
				"/IoPago/Categorie/" + categoria.replace(" ", "%20") + "/"
						+ anno, this);
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
			if (procapite) {
				itemPro = downloadedItems;
				int popolazione = 0;
				List<Item> newList = new ArrayList<Item>();
				for (Item item : downloadedItems) {
					popolazione += item.getAbitanti();
				}
				for (int i = 0; i < itemAll.size(); i++) {
					Item tmp = itemAll.get(i);

					newList.add(new Item(tmp.getRegione(), tmp.getCategoria(),
							tmp.getSpesa() / itemPro.get(i).getAbitanti(), tmp
									.getAnno(), 0));
				}

				mTorta.setChartValues(newList);
			} else {
				allDown = true;
				mTorta.setChartValues(downloadedItems);
				itemAll = downloadedItems;
			}

		}
	}

}
