package it.polito.hackattoni.json;

import it.polito.hackattoni.eiopago.Item;

import java.util.List;

public interface OnDownloadVotiCompleted {
	void onDownloadVotiCompleted(List<VotoConCategoria> downloadedItems, boolean error);
}
