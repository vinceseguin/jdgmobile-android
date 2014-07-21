package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.ContentValues;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IJsonBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableContext;
import ca.qc.jeuxdegenie.jdgmobile.view.CalendarFragment;

public class CalendarEventSqlDataUpdateJsonWorker implements IJsonBackgroundWorker {

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
	
	private String url = "backend/WS/CalendarWS.php?method=getEvents";
	private CalendarFragment context;
	
	public CalendarEventSqlDataUpdateJsonWorker(CalendarFragment context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url;
	}
	
	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(JSONArray result) {
		IUpdatableContext frag = (IUpdatableContext) context;		
		SqLiteDAO sqLiteDAO = frag.getSqLiteDAO();
		
		try {
			//kinda useless.. would need to make it appear before the WS call..
			//Toast.makeText(context.getActivity(), context.getText(R.string.calendarSynch), Toast.LENGTH_LONG).show();
			
			for (int i=0; i<result.length(); i++) {				
				
				Event event = new Event(result.getJSONObject(i));				
				
				ContentValues values = new ContentValues();
				values.put(TAG_ID, event.getId());
				values.put(TAG_DAY, event.getDay());
				values.put(TAG_START_DATE, event.getStartDate());
				values.put(TAG_END_DATE, event.getEndDate());
				values.put(TAG_START_TIME, event.getStartTime());
				values.put(TAG_END_TIME, event.getEndTime());
				values.put(TAG_NAME, event.getName());
				values.put(TAG_DESCRIPTION, event.getDescription());
				values.put(TAG_LOCATION, event.getLocation());
				values.put(TAG_HAS_DESCRIPTION, event.hasDescription());
				
				sqLiteDAO.insertData(values);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
