package it.polito.hackattoni.proposte;

import java.util.List;

public interface OnDownloadPropostaCompleted {
	void onDownDownloadPropostaCompleted(List<Proposta> downloadedItems,
			boolean error);

}
