package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.List;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultAdapter extends ArrayAdapter<Result> {

	private Context context;
	private int resource;
	private List<Result> objects = null;
	
	public ResultAdapter(Context context, int resource, List<Result> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		ResultHolder holder = null;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(resource, parent, false);
		
		holder = new ResultHolder();
		holder.txtName = (TextView) row.findViewById(R.id.text1);
		
		row.setTag(holder);
		
		final Result result = objects.get(position);
		holder.txtName.setText(result.getName());
		
		if (!result.isLeaf()) {
			
			row.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			 
					// set title
					alertDialogBuilder.setTitle("Your Title");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("I have children!")
						.setCancelable(true);
		 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
				}
				
			});
		}
		
		return row;
	}
	
	static class ResultHolder 
	{
		TextView txtName;
	}
}
