package ca.qc.jeuxdegenie.jdgmobile.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;

/**
 * 
 * @author vgentilcore
 */
public class CalendarFragmentAdapter extends ArrayAdapter<Event> {

	private Context context;
	private int resource;
	private List<Event> objects = null;

	public CalendarFragmentAdapter(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		EventHolder holder = null;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(resource, parent, false);
		
		holder = new EventHolder();
		holder.txtStartTime = (TextView) row.findViewById(R.id.txtStartTime);
		holder.txtName = (TextView) row.findViewById(R.id.txtName);
		holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
		holder.txtLocation = (TextView) row.findViewById(R.id.txtLocation);
		
		row.setTag(holder);
		
		final Event event = objects.get(position);
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
