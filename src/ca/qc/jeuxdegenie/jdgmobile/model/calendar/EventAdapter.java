package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.view.EventDetailActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author vgentilcore
 */
public class EventAdapter extends ArrayAdapter<Event> {

	private Context context;
	private int resource;
	private Event[] objects = null;
	
	public EventAdapter(Context context, int resource, Event[] objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		EventHolder holder = null;
		
		// if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);
			
			TableRow tableRow1 = (TableRow) row.findViewById(R.id.tableRow1);
			TableRow tableRow2 = (TableRow) row.findViewById(R.id.tableRow2);
			TableRow tableRow3 = (TableRow) row.findViewById(R.id.tableRow3);
			
			holder = new EventHolder();
			holder.txtStartTime = (TextView) tableRow1.findViewById(R.id.txtStartTime);
			holder.imgIcon = (ImageView) tableRow1.findViewById(R.id.imgIcon);
			holder.txtName = (TextView) tableRow2.findViewById(R.id.txtName);
			holder.txtLocation = (TextView) tableRow3.findViewById(R.id.txtLocation);			
						
			row.setTag(holder);
		/* } else {
			holder = (EventHolder) row.getTag();
		} */
		
		final Event event = objects[position];
		holder.txtStartTime.setText(event.getStartTime());
		holder.txtName.setText(event.getName());
		holder.txtLocation.setText(event.getLocation());
		
		if (event.hasDescription()) {
			
			holder.imgIcon.setImageResource(R.drawable.chevron_normal);
			
			row.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(context, EventDetailActivity.class);
					intent.putExtra("description", event.getDescription());
					context.startActivity(intent);
				}
			});
		}
		
		return row;
	}

	static class EventHolder
	{
		TextView txtStartTime;
		TextView txtName;
		TextView txtLocation;
		ImageView imgIcon;
	}
}
