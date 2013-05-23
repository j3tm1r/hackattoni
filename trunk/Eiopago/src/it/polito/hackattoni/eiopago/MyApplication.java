package it.polito.hackattoni.eiopago;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class MyApplication extends Application {
	public static List<Item> getSpesaTotalePerCategoria() {
		List<Item> myList = new ArrayList<Item>();
		
		myList.add(new Item("","sanita",220,2008));
		myList.add(new Item("","difesa",23,2008));
		myList.add(new Item("","istruzione",340,2008));
		myList.add(new Item("","giustizia",267,2008));
		myList.add(new Item("","turismo",120,2008));
		myList.add(new Item("","amministrazione",190,2008));
		myList.add(new Item("","industria",210,2008));
		
		
		return myList;
	}
}
