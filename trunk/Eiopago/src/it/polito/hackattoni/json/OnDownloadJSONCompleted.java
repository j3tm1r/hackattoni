package it.polito.hackattoni.json;

import it.polito.hackattoni.eiopago.Item;

import java.util.List;

public interface OnDownloadJSONCompleted {
	void onDownDownloadJSONCompleted(List<Item> downloadedItems, boolean error);
}
