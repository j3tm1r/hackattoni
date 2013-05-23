package it.polito.hackattoni.eiopago;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EIoPagoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eio_pago);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eio_pago, menu);
        return true;
    }
    
}
