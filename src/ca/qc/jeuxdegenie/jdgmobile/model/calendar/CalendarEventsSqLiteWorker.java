package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import android.database.Cursor;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.ISqLiteBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.HoraireFragment;

public class CalendarEventsSqLiteWorker implements ISqLiteBackgroundWorker {

	private String tableName = "mobile_event";
	private HoraireFragment context;
	
	public CalendarEventsSqLiteWorker(HoraireFragment context) {
		this.context = context;
	}
	
	@Override
	public String getTableName() {
		return tableName;
	}
	
	@Override
	public void doWork(Cursor cursor) {
		if (cursor.getCount() == 0) {
			return;
		}
		
		SortedMap<CharSequence, List<Event>> data = new TreeMap<CharSequence, List<Event>>();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA_FRENCH);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM", Locale.CANADA_FRENCH);
		
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
		    Event event = new Event(cursor);
		    
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
	}
}