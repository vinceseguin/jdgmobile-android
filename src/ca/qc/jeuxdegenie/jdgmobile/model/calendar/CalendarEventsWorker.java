package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IJsonBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.HoraireFragment;

public class CalendarEventsWorker implements IJsonBackgroundWorker {

	private String url = "backend/WS/CalendarWS.php?method=getEvents";
	private HoraireFragment context;
	
	public CalendarEventsWorker(HoraireFragment context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url;
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(JSONArray result) {
		SortedMap<CharSequence, List<Event>> data = new TreeMap<CharSequence, List<Event>>();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA_FRENCH);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM", Locale.CANADA_FRENCH);
		
		try {
			for (int i=0; i<result.length(); i++) {
				
				Event event = new Event(result.getJSONObject(i));
				
				Date parsedDate;
				try {
					parsedDate = sdf1.parse(event.getStartDate());
					String formattedDate = sdf2.format(parsedDate);
								
					if (!data.keySet().contains(formattedDate)) {
						data.put(formattedDate, new ArrayList<Event>());
					}

					data.get(formattedDate).add(event);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			
			context.updateContent(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
