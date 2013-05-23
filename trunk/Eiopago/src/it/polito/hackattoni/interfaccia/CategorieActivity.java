package it.polito.hackattoni.interfaccia;

import it.polito.hackattoni.eiopago.R;
import it.polito.hackattoni.eiopago.R.layout;
import it.polito.hackattoni.eiopago.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CategorieActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categorie);
		
		//LinearLayout v = (LinearLayout) findViewById(R.id.Linear1);

		ListView lv=(ListView) findViewById(R.id.lv);
		
		lv.setAdapter(new CategorieAdapter(new int[]{33, 50, 99, 20}));
		
		//System.out.println(lv.getClass().getCanonicalName());
		//Categoria myView=(Categoria) v1;



//	    (this, 60, "scrivi", Color.BLUE);
	    
//	    v.addView(myView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
	
	}
		

}
