package ca.qc.jeuxdegenie.jdgmobile.controller;

import org.json.JSONArray;

import android.os.AsyncTask;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.IJsonBackgroundWorker;

public class JsonDAO extends AsyncTask<String, String, JSONArray>{

	private IJsonBackgroundWorker backgroundWorker;
	
	public JsonDAO(IJsonBackgroundWorker backgroundWorker) {
		this.backgroundWorker = backgroundWorker;
	}

	@Override
	protected JSONArray doInBackground(String... params) {
		JSONParser jParser = new JSONParser();
		JSONArray json = jParser.getJSONArrayFromURL(backgroundWorker.getUrl()); 
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
		backgroundWorker.doWork(result);	
	}

}
