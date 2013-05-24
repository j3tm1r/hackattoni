package it.polito.hackattoni.proposte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.polito.hackattoni.eiopago.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ActivityProposta extends Activity implements
		OnDownloadPropostaCompleted {

	private ListView mHolderProposte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proposta);

		mHolderProposte = (ListView) findViewById(R.id.holderProposte);

		DownloadProposte myDownP = new DownloadProposte(
				"/IoPago/Proposte/piemonte", this);
		myDownP.execute();
	}

	@Override
	public void onDownDownloadPropostaCompleted(List<Proposta> downloadedItems,
			boolean error) {
		mHolderProposte.setAdapter(new AdapterProposte(downloadedItems));
	}

	private class AdapterProposte extends BaseAdapter {

		private List<Proposta> mList;
		private android.view.LayoutInflater inflater;

		public AdapterProposte(List<Proposta> downloadedItems) {
			inflater = (android.view.LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			mList = downloadedItems;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View vi = arg1;

			if (arg1 == null)
				vi = inflater.inflate(R.layout.layout_proposta, null);

			TextView arg, reg, voti, dettaglio;

			arg = (TextView) vi.findViewById(R.id.argomento);
			reg = (TextView) vi.findViewById(R.id.regione);
			voti = (TextView) vi.findViewById(R.id.voti);
			dettaglio = (TextView) vi.findViewById(R.id.dettaglio);

			dettaglio.setText(mList.get(arg0).getProposta());
			arg.setText(mList.get(arg0).getOggetto().toUpperCase());
			reg.setText(mList.get(arg0).getRegione().toUpperCase());
			voti.setText("Voti " + mList.get(arg0).getVoti());

			return vi;
		}

	}

	private class DownloadProposte extends AsyncTask<Void, Integer, Void> {

		private String path; // cartella nel server (es. "/IoPago/Categorie")
		private List<Proposta> myList;
		private OnDownloadPropostaCompleted odjc;
		private boolean myError = false;

		public DownloadProposte(String path, OnDownloadPropostaCompleted odjc) {
			this.path = path;
			myList = new ArrayList<Proposta>();
			this.odjc = odjc;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Recupero il file JSON dal server:
			JSONArray myJSONArray = getJSONArray();
			try {
				for (int i = 0; i < myJSONArray.length(); i++) {
					JSONObject jo = myJSONArray.getJSONObject(i);
					Proposta tmpItem = new Proposta(jo.getString("oggetto"),
							jo.getString("proposta"), jo.getString("utente"),
							jo.getString("regione"), jo.getInt("id_proposta"),
							jo.getInt("nrVoti"));
					myList.add(tmpItem);
				}

			} catch (JSONException je) {
				myError = true;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			odjc.onDownDownloadPropostaCompleted(myList, myError);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// ProgressBar pb = (ProgressBar)
			// myActivity.findViewById(R.id.progressBar1);
			// pb.setProgress(progress[0]);
		}

		private JSONArray getJSONArray() {
			// Esempio: Json.getJSONArray("/task3");
			String host = "192.168.0.2";
			int port = 8080;
			// String msg = ""; //Es. "action=begin"

			try {
				// URL url = new URL("http://softeng.polito.it/task3");
				URL url = new URL("http", host, port, path);
				// URLConnection conn = url.openConnection();
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setDoInput(true);
				// conn.setDoOutput(true);
				// conn.setAllowUserInteraction(true);
				conn.connect();
				// conn.getOutputStream().write(msg.getBytes());
				// conn.getOutputStream().close();
				StringBuilder sb = new StringBuilder();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null)
					sb.append(line);
				// in.close();
				conn.disconnect();
				String tmpString = sb.toString();
				return new JSONArray(tmpString);
			} catch (Exception e) {
				myError = true;
				return new JSONArray();
			}

		}

		// Questa serve solo a visualizzare un dialog
		public void visualizzaDialogo(final Activity myActivity, String msg) {
			AlertDialog.Builder myBuilder = new AlertDialog.Builder(myActivity);
			myBuilder.setTitle("Avviso");
			myBuilder.setMessage(msg);
			myBuilder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ActivityProposta.this.finish();

						}
					});
			AlertDialog myAlertDialog = myBuilder.create();
			myAlertDialog.show();
		}
	}

}
