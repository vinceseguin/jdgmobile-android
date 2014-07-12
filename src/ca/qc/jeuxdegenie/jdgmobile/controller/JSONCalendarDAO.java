package ca.qc.jeuxdegenie.jdgmobile.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.view.HoraireFragment;

/**
 * 
 * @author vgentilcore
 */
public class JSONCalendarDAO extends AsyncTask<String, String, JSONArray> {

	private static String url = "http://192.168.1.112/JDGMobile-Web/backend/WS/CalendarWS.php?method=getEvents";
	
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
		JSONArray json = jParser.getJSONArrayFromURL(url);
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
		
		SortedMap<CharSequence, List<Event>> data = new TreeMap<CharSequence, List<Event>>();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA_FRENCH);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM", Locale.CANADA_FRENCH);
		
		try {
			for (int i=0; i<result.length(); i++) {
				
				Event event = new Event(result.getJSONObject(i));
				
				Date parsedDate = sdf1.parse(event.getStartDate());				
				String formattedDate = sdf2.format(parsedDate);
				
				if (!data.keySet().contains(formattedDate)) {
					data.put(formattedDate, new ArrayList<Event>());
				}
				
				data.get(formattedDate).add(event);
			}
			
			frag.updateContent(data);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
