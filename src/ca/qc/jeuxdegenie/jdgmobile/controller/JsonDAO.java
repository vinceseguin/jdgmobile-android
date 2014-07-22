package ca.qc.jeuxdegenie.jdgmobile.controller;

import org.json.JSONArray;

import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.IJsonBackgroundWorker;
import android.os.AsyncTask;

public class JsonDAO extends AsyncTask<String, String, JSONArray>{

	private IJsonBackgroundWorker backgroundWorker;
	
	public JsonDAO(IJsonBackgroundWorker backgroundWorker) {
		this.backgroundWorker = backgroundWorker;
	}

	@Override
	protected JSONArray doInBackground(String... params) {
		JsonParser jParser = new JsonParser();
		JSONArray json = jParser.getJSONArrayFromURL(backgroundWorker.getUrl()); 
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
		backgroundWorker.doWork(result);	
	}
}
