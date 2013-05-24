package it.polito.hackattoni.json;

import java.util.List;

import org.json.JSONArray;

import it.polito.hackattoni.eiopago.Item;
import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import it.polito.hackattoni.eiopago.VotaActivity;
import it.polito.hackattoni.visualizzazioni.Legenda;
import it.polito.hackattoni.visualizzazioni.VistaQuadrati;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class GraficoQuadratiActivity extends Activity implements
		OnDownloadJSONCompleted {
	private DownloadJSONArrayTask myDownloadJSONArrayTask;
	private TextView myTextView;
	private FrameLayout myFrameLayout;
	private String regione;
	private ProgressBar myProgressBar;
	private int anno;
	private Button myButton;
	private List<TextView> mList;
	private ListView lv;
	private LinearLayout listHolder;
	private Button chiudiLegenda;
	private Spinner mSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Recupero l'intent
		regione = getIntent().getStringExtra("regione");
		anno = getIntent().getIntExtra("anno", 2008);

		setContentView(R.layout.activity_grafico_quadrati);
		myTextView = (TextView) findViewById(R.id.textView);
		myTextView.setText("Regione: " + regione + "\n" + "Anno: " + anno);
		myFrameLayout = (FrameLayout) findViewById(R.id.frame_layout_grafico_quadrati);
		myProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		myButton = (Button) findViewById(R.id.button1);
		listHolder = (LinearLayout) findViewById(R.id.legenda);
		lv = (ListView) findViewById(R.id.legendaHolder);
		Spinner mSpinner = (Spinner) findViewById(R.id.spinner1);
		
		// Create an ArrayAdapter using the string array and a default spinner
				// layout
		
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
						this, R.array.anni_spinner,
						android.R.layout.simple_spinner_item);
				// Specify the layout to use when the list of choices appears
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner

				mSpinner.setAdapter(adapter);
				mSpinner.setSelection(anno - 1996);
				
				mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					private boolean firstTime=true;
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
							long arg3) {
						/*
						if (mSpesaPro.isChecked())
							mSpesaPro.toggle();
						procapite = false;
						myDownloadJSONArrayTask = new DownloadJSONArrayTask(
								"/IoPago/Categorie/" + categoria.replace(" ", "%20")  + "/" + (1996 + pos),
								GraficoTortaActivity.this);
						myDownloadJSONArrayTask.execute();
						*/
						if(firstTime) {
							firstTime=false;
							return;
						}
						int anno = 1996 + pos;
						Intent i = new Intent(getApplicationContext(),GraficoQuadratiActivity.class);
				        i.putExtra("regione", regione);
				        i.putExtra("anno",anno);
				        startActivity(i);
				        finish();
				       
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
				
		
		
		chiudiLegenda = (Button) findViewById(R.id.chiudiLegend);
		chiudiLegenda.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AlphaAnimation a = new AlphaAnimation(1, 0);
				a.setDuration(500);
				a.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						
					}
				});

				listHolder.setAnimation(a);
				a.start();
				listHolder.setVisibility(View.GONE);
			}
		});
		myDownloadJSONArrayTask = new DownloadJSONArrayTask("/IoPago/Regioni/"
				+ regione + "/" + anno, this);
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
			myProgressBar.setVisibility(View.GONE);
			//mSpinner.setVisibility(View.VISIBLE);
			disegnaGraficoQuadrato(downloadedItems);
		}
	}

	private void disegnaGraficoQuadrato(List<Item> iList) {
		/*
		 * DisplayMetrics displayMetrics = new DisplayMetrics();
		 * getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		 * int width = displayMetrics.widthPixels; int height =
		 * displayMetrics.heightPixels;
		 */
		int width = myFrameLayout.getWidth();
		int height = myFrameLayout.getHeight();
		VistaQuadrati tmpVistaQuadrati = new VistaQuadrati(this, iList, width,
				height);
		myFrameLayout.addView(tmpVistaQuadrati);
		Legenda tmpLegenda = new Legenda(this,
				tmpVistaQuadrati.getMyListOfLati());
		mList = tmpLegenda.getTextViewList();
		// int padding = 15;
		// for (TextView textView : tmpList) {
		// padding += 15;
		// textView.setPadding(0, padding, 0, 0);
		// myFrameLayout.addView(textView);
		// }

		myButton.setVisibility(View.VISIBLE);
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lv.setAdapter(new LegendaAdpater(mList));
				listHolder.setVisibility(View.VISIBLE);
				AlphaAnimation a = new AlphaAnimation(0, 1);
				a.setDuration(500);

				listHolder.setAnimation(a);
				a.start();

				// AlertDialog.Builder myBuilder = new
				// AlertDialog.Builder(GraficoQuadratiActivity.this);
				// myBuilder.setTitle("Avviso");
				// myBuilder.setMessage("ciao");
				// myBuilder.setPositiveButton("Ok",
				// new DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog, int which) {
				// dialog.dismiss();
				//
				// }
				// });
				// AlertDialog myAlertDialog = myBuilder.create();
				// myAlertDialog.show();;
				
				//Visualizzo votazioni:
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int pos, long itemId) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplication(),VotaActivity.class);
						List<Item> myListOfItem = myDownloadJSONArrayTask.getMyList();
						Item myItem = myListOfItem.get(pos);
						String regioneTmp = myItem.getRegione().replace(" ", "%20");
						String categoriaTmp = myItem.getCategoria().replace(" ", "%20");
						i.putExtra("regione", regioneTmp);
						i.putExtra("categoria", categoriaTmp);
						startActivity(i);
					}
				});

			}
		});

	}

	private class LegendaAdpater extends BaseAdapter {

		private List<TextView> mList;

		public LegendaAdpater(List<TextView> lv) {
			this.mList = lv;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return this.mList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0 + 1;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return this.mList.get(arg0);
		}
	}

}