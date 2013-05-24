package it.polito.hackattoni.json;

import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.id;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class VotaActivity extends Activity {
	private String regione;
	private String categoria;
	private TextView myTextView;
	private RatingBar myRatingBar;
	private Button myButton;
	private float voto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vota);
		Intent i = getIntent();
		regione=i.getStringExtra("regione");
		categoria=i.getStringExtra("categoria");
		myButton=(Button) findViewById(R.id.button1);
		
		myRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
		myRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				voto=rating;
				
			}
		});
		
		myButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),VotaActivity2.class);
				i.putExtra("regione", regione);
				i.putExtra("categoria", categoria);
				i.putExtra("voto", voto);
				startActivity(i);
				finish();
			}
		});

		myTextView = (TextView) findViewById(R.id.textView);
		myTextView.setTextSize(20);
		myTextView.setText("HAI SELEZIONATO:\nRegione: "+regione+"\n"+"Categoria: "+categoria.replace("%20", " ")+"\n\nNel prossimo bilancio della regione "+regione+ ", quanto vorresti fosse valorizzata la categoria "+ categoria.replace("%20", " ")+"?");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vota, menu);
		return true;
	}

}
