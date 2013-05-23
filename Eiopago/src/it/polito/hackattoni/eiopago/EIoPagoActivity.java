package it.polito.hackattoni.eiopago;

import it.polito.hackattoni.interfaccia.CategorieActivityV2;
import it.polito.hackattoni.json.GraficoQuadratiActivity;
import it.polito.hackattoni.json.GraficoTortaActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EIoPagoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eio_pago);
        Button barreButton = (Button) findViewById(R.id.button1);
        barreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        Intent i = new Intent(EIoPagoActivity.this,GraficoQuadratiActivity.class);
				startActivity(i);
			}
		});
        
        Button jsonButton = (Button) findViewById(R.id.button2);
        jsonButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        Intent i = new Intent(EIoPagoActivity.this,GraficoTortaActivity.class);
				startActivity(i);
			}
		});

        Button barre2Button = (Button) findViewById(R.id.button3);
        barre2Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        Intent i = new Intent(EIoPagoActivity.this,CategorieActivityV2.class);
				startActivity(i);
			}
		});


        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eio_pago, menu);
        return true;
    }
    
}
