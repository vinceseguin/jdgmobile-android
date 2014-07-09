package ca.qc.jeuxdegenie.jdgmobile.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.view.HoraireFragment;

/**
 * 
 * @author vgentilcore
 */
public class JSONCalendarDAO extends AsyncTask<String, String, JSONArray> {

	private static String url = "http://192.168.2.15/public/JDGMobile-Web/backend/WS/CalendarWS.php?method=getEvents";
	
	private static final String TAG_ID = "id";
	private static final String TAG_DAY = "day";
	private static final String TAG_START_DATE = "startDate";
	private static final String TAG_END_DATE = "endDate";
	private static final String TAG_START_TIME = "startTime";
	private static final String TAG_END_TIME = "endTime";
	private static final String TAG_NAME = "name";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_HAS_DESCRIPTION = "hasDescription";
	
	private HoraireFragment frag;
	
	public JSONCalendarDAO(HoraireFragment frag) {
		this.frag = frag;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected JSONArray doInBackground(String... params) {
		JSONParser jParser = new JSONParser();
		JSONArray json = jParser.getJSONFromUrl(url);
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
		try {
			List<Event> events = new ArrayList<Event>();
			
			for (int i=0; i<result.length(); i++) {
				JSONObject obj = result.getJSONObject(i);
				Event event = new Event(obj.getString(TAG_DAY), obj.getString(TAG_START_DATE), 
										obj.getString(TAG_END_DATE), obj.getString(TAG_START_TIME), 
										obj.getString(TAG_END_TIME), obj.getString(TAG_NAME), 
										obj.getString(TAG_DESCRIPTION), obj.getString(TAG_LOCATION), 
										obj.getString(TAG_HAS_DESCRIPTION));
				events.add(event);
			}
			
			frag.updateContent(events.toArray(new Event[events.size()]));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
