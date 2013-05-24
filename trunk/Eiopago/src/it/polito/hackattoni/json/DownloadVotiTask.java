package it.polito.hackattoni.json;

import it.polito.hackattoni.eiopago.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class DownloadVotiTask extends AsyncTask<Void, Integer, Void> {

	private String path; // cartella nel server (es. "/IoPago/Categorie")
	private List<VotoConCategoria> myList;
	public List<VotoConCategoria> getMyList() {
		return myList;
	}

	private OnDownloadVotiCompleted odjc;
	private boolean myError = false;

	public DownloadVotiTask(String path, OnDownloadVotiCompleted odjc) {
		this.path = path;
		myList = new ArrayList<VotoConCategoria>();
		this.odjc = odjc;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// Recupero il file JSON dal server:
		JSONArray myJSONArray = getJSONArray();
		try {
			for (int i = 0; i < myJSONArray.length(); i++) {
				JSONObject jo = myJSONArray.getJSONObject(i);
				VotoConCategoria tmpVotoConCategoria = new VotoConCategoria(jo.getString("categoria"), jo.getDouble("media"));
				myList.add(tmpVotoConCategoria);
			}

		} catch (JSONException je) {
			myError = true;
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void v) {
		odjc.onDownloadVotiCompleted(myList, myError);
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
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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
						myActivity.finish();

					}
				});
		AlertDialog myAlertDialog = myBuilder.create();
		myAlertDialog.show();
	}
}