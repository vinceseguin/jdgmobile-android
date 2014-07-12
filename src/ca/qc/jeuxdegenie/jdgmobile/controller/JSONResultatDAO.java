package ca.qc.jeuxdegenie.jdgmobile.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatFragment;
import android.os.AsyncTask;

public class JSONResultatDAO extends AsyncTask<String, String, JSONObject> {

	private static String url = "http://192.168.2.15/public/JDGMobile-Web/backend/WS/CompetitionWS.php?method=getCompetition";
	
	private static final String TAG_ITEMS = "items";
	
	private ResultatFragment frag;
	
	public JSONResultatDAO(ResultatFragment frag) {
		this.frag = frag;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected JSONObject doInBackground(String... arg0) {
		JSONParser jParser = new JSONParser();
		JSONObject json = jParser.getJSONObjectFromURL(url);
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		
		List<Result> data = new ArrayList<Result>();
		
		try {
			JSONArray items = result.getJSONArray(TAG_ITEMS);
			
			for (int i=0; i<items.length(); i++) {
				Result res = new Result(items.getJSONObject(i));
				data.add(res);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//frag.updateContent(data);
	}
}
